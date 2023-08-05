/**
 *
 */
package com.nekopiano.scala.sandbox.download

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
import scala.xml.Text

/**
 * @author La Musique
 *
 */
object PageDownloadTest {

  def main(args: Array[String]): Unit = {

    //http://www.sample.jp/products/

    //    val h = new Http
    //    val req = url("http://www.sample.jp/products/") >\ "UTF-8"
    //    val html = h(req as_source)
    //    println(html)
    //    html.foreach(e => println(e))

    //    val data = Resource.fromURL("http://www.sample.jp/products/").byteArray
    //    println("downloading: %s ..." format url)
    //    Resource.fromFile(new java.io.File("test.txt")).write(data)

    val h = new Http
    val req = url("http://www.sample.jp/products/") >\ "UTF-8"
    val html = h(req as_str)

    val rootNode = toNode(html)
    // products/multi/ep775a/
    //  val links = rootNode \\ "a" filter (_ \ "@href" contains "/products/multi/ep775a/")
    //    val links = rootNode \\ "a" filter (e => {
    //      val href = e \ "@href"
    //      println("href=" + href)
    //      href.text contains "products"
    //    })
    //val links = rootNode \\ "@href" filter (_.text contains "products")
    val links = rootNode \\ "@href" filter (_.text matches "/products/[a-z]+/[a-z0-9]+/")

    println("links.size=" + links.size)
    val pages = links.map(a => {
      a.text + "shiyo.htm"
    })

    pages.foreach(url => {
      println(url)
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
