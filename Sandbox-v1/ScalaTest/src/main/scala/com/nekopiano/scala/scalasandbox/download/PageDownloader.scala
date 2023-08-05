/**
 *
 */
package com.nekopiano.scala.scalasandbox.download

import dispatch.classic.url
import dispatch.classic.Http
import java.io.StringReader
import nu.validator.htmlparser.common.XmlViolationPolicy
import nu.validator.htmlparser.sax.HtmlParser
import org.xml.sax.InputSource
import scalax.io.JavaConverters._
import scalax.io.Resource
import scala.xml.Node
import scala.xml.parsing.NoBindingFactoryAdapter

/**
 * @author La Musique
 *
 */
object PageDownloader {

  def main(args: Array[String]): Unit = {

    val h = new Http
    val req = url("http://www.sample.jp/products/") >\ "UTF-8"
    val html = h(req as_str)

    val rootNode = toNode(html)
    val links = rootNode \\ "@href" filter (_.text matches "/products/[a-z]+/[a-z0-9]+/")

    val pages = links.map(a => {
      "http://www.sample.jp" + a.text + "shiyo.htm"
    }).sorted.distinct
    println("pages.size=" + pages.size)

    pages.foreach(url => {
      println(url)
      val data = Resource.fromURL(url).byteArray
      println("downloading: %s ..." format url)
      val r = "[a-z]+/[a-z0-9]+/shiyo.htm".r
      val productName = r.findAllIn(url).matchData.mkString.dropRight(10).replace("/", "-")
      Resource.fromFile(new java.io.File("samplepages\\" + productName + ".html")).write(data)
    })

  }

  def toNode(str: String): Node = {
    val hp = new HtmlParser
    hp.setNamePolicy(XmlViolationPolicy.ALLOW)

    val saxer = new NoBindingFactoryAdapter
    hp.setContentHandler(saxer)
    hp.parse(new InputSource(new StringReader(str)))

    saxer.rootElem
  }

}

