/**
 *
 */
package com.nekopiano.scala.rest.client

import dispatch._
import Http._
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
import scala.xml.PrettyProduct
import java.net.URL
import scala.io.Source
import scala.io.Codec
import java.net.URI
import scala.xml.XML
import java.net.HttpURLConnection
import java.net.PasswordAuthentication
import java.net.Authenticator
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import org.jboss.netty.handler.codec.base64.Base64Encoder

/**
 * @author La Musique
 *
 */
object RestClient {

  val URI_EP905F = "http://localhost:9001/sample/rest/catalogs/Default/catalogversions/Staged/products/EP-905F"

  def main(args: Array[String]): Unit = {

    //  val url = new URL("http://127.0.0.1:9001/sample/rest/abcitems");
    //val connection =  url.openConnection.asInstanceOf[HttpURLConnection];
    //connection.setRequestProperty("Authorization:",
    //"Basic "+codec.encodeBase64String(("username:password").getBytes());

    val auth = new MyAuthenticator("admin", "admin")
    java.net.Authenticator.setDefault(auth)
    //    val url = new URL("http://localhost:9001/sample/rest/abcitems")
    val url = new URL("http", "localhost", 9001, "/sample/rest/abcitems")
    val connection = url.openConnection().asInstanceOf[HttpURLConnection];
           connection.setRequestMethod("GET");
//            connection.setDoOutput(true);
//           val encoding = Base64Encoder.encode("test1:test1")
            connection.setRequestProperty  ("Authorization", "Basic " + "credential=");
            val content = connection.getInputStream().asInstanceOf[InputStream];
            val in   = 
                new BufferedReader (new InputStreamReader (content));
            var line = ""
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
//    val extractSource = Source.fromURL(url);
//    println(extractSource.mkString)

    import java.net.{ Authenticator, PasswordAuthentication }
    Authenticator.setDefault(
      new Authenticator {
        override def getPasswordAuthentication =
          new PasswordAuthentication("admin", "admin".toCharArray)
      })

    // getByDispatch()

    // Http Request
    //val http = new Http
    //val json = http(:/("api.twitter.com") / "1/users/show.json" <<? Map("screen_name" -> "aloiscochard") >~ { _.getLines.mkString })

    //val src = Source.fromURL(new URL(URI_EP905F))

    // URLから文字列で読み込む
    //    val source = Source.fromURL("http://localhost:9001/sample/rest/catalogs/Default/catalogversions/Staged/abcitems")
    //    val source = Source.fromURL("http://localhost:9001/hac")
    //    val source = Source.fromURL("http://localhost:9001/sample/rest/abcitems")
    //    val source = Source.fromURL("http://admin:admin@localhost:9001/sample/rest/abcitems")
    val source = Source.fromURL("http://127.0.0.1:9001/sample/rest/abcitems")

    println(source.mkString)
    // 文字列からXMLオブジェクトを作る
    val xml = XML.loadString(source.getLines.mkString)

    //  val src = getSource()
    //    println(src)
    //    val rootNode = toNode(src.mkString)

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

  def getByDispatch() = {

    val h = new Http
    //    val req = url("http://localhost:9001/sample/rest/catalogs/Default/catalogversions/Staged/products/EP-905F") <:< Map("Accept-Language" -> "ja") >\ "UTF-8"
    //    val req = url("http://localhost:9001/sample/rest/catalogs/Default/catalogversions/Staged/products/EP-905F").as_!("admin", "admin") <:< Map("Accept-Language" -> "ja") >\ "UTF-8"
    //    val req = url("http://localhost:9001/sample/rest/catalogs/Default/catalogversions/Staged/products/EP-905F").as("admin", "admin") <:< Map("Accept-Language" -> "ja") >\ "UTF-8"
    //    val req = url("http://localhost:9001/sample/rest/abcitems").as("admin", "admin") <:< Map("Accept-Language" -> "ja") >\ "UTF-8"
    val req = url("http://localhost:9001/sample/rest/catalogs/Default/catalogversions/Staged/products/EP-905F").as("admin", "admin") >\ "UTF-8"
    val responsePayload = h(req as_str)

    val rootNode = toNode(responsePayload)
    val pp = new PrettyProduct(80, 2)
    println("rootNode=" + rootNode)
    println("formated=" + pp.format(rootNode))
  }

  class MyAuthenticator(user: String, pass: String) extends java.net.Authenticator {
    override def getPasswordAuthentication(): PasswordAuthentication =
      {
        return new PasswordAuthentication(this.user, this.pass.toCharArray);
      }
  }
}

