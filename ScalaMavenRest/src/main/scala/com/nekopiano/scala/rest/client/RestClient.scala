/**
 *
 */
package com.nekopiano.scala.rest.client

import java.io.StringReader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

import scala.io.Codec
import scala.io.Source
import scala.xml.Node
import scala.xml.PrettyProduct
import scala.xml.XML
import scala.xml.parsing.NoBindingFactoryAdapter

import org.xml.sax.InputSource

import dispatch.classic.Http
import dispatch.classic.Request.toHandlerVerbs
import dispatch.classic.Request.toRequestVerbs
import dispatch.classic.url
import nu.validator.htmlparser.common.XmlViolationPolicy
import nu.validator.htmlparser.sax.HtmlParser

/**
 * @author La Musique
 *
 */
object RestClient {

  val BASE_PASS = "credential="
  val URI_EP905F = "http://localhost:9001/sample/rest/catalogs/Default/catalogversions/Staged/products/EP-905F"

  def main(args: Array[String]): Unit = {

    val s = Source.fromFile("requests/REST-requests.txt")
    for (line <- s.getLines) {
      var split = line.split(',')
      getByDispatch(split(0), split(1))
    }

  }

  def toNode(str: String): Node = {
    val hp = new HtmlParser
    hp.setNamePolicy(XmlViolationPolicy.ALLOW)

    val saxer = new NoBindingFactoryAdapter
    hp.setContentHandler(saxer)
    hp.parse(new InputSource(new StringReader(str)))

    saxer.rootElem
  }

  def getSource(url: String) = {
    val in = new URL(url).openStream

    val buf = Stream.continually { in.read }.takeWhile { -1 != }.map { _.byteValue }.toArray

    implicit val codec = {
      val src = Source.fromBytes(buf, "UTF-8")
      val Charset = """.*content.*charset\s*=\s*([0-9a-z|\-|_]+).*""".r
      src.getLines.collect {
        case Charset(cs) => cs
      }.toTraversable.headOption.map { cs => Codec(cs) }.getOrElse { Codec.default }
    }
    Source.fromBytes(buf)
  }

  def getByDispatch(name: String, uri: String) = {

    val h = new Http
//    val req = url(uri) <:< Map("Authorization" -> "Basic credential=", "Accept-Languate" -> "ja") >\ "UTF-8"
    val req = url(uri) <:< Map("Authorization" -> "Basic credential=") >\ "UTF-8"

    val responsePayload = h(req as_str)
    println("responsePayload=" + responsePayload)
    //val rootNode = toNode(responsePayload)
    //    println("rootNode=" + rootNode)

    val xml = XML.loadString(responsePayload)
    println("xml=" + xml)
    val pp = new PrettyProduct(500, 2)
    val formattedXml = pp.format(xml)
    println("formattedXml=" + formattedXml)

    Files.write(Paths.get("responses/payloads/" + name + ".xml"), xml.mkString.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)
    Files.write(Paths.get("responses/payloads/formatted-" + name + ".xml"), formattedXml.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)

  }

}

