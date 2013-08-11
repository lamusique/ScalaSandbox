/**
 *
 */
package com.nekopiano.scala.rest

import java.io.StringReader
import scala.annotation.tailrec
import scala.io.Source
import scala.xml.Atom
import scala.xml.Comment
import scala.xml.Node
import scala.xml.Text
import scala.xml.parsing.NoBindingFactoryAdapter
import org.xml.sax.InputSource
import nu.validator.htmlparser.common.XmlViolationPolicy
import nu.validator.htmlparser.sax.HtmlParser
import scala.xml.Elem
import org.w3c.dom.Document
import scala.xml.parsing.ConstructingParser
import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import scala.xml.XML

/**
 * @author La Musique
 *
 */
object XmlSAXReader {

  def main(args: Array[String]): Unit = {

    //    val xmlSource = Source.fromFile("responses/payloads/2.AbcProducts-EP-805.xml")
    //    val rootNode = toNode(xmlSource.mkString)
    //    val rootNode = XML.loadFile("responses/payloads/1.ApprovedList.xml")
    //    val rootNode = XML.loadFile("responses/payloads/2.AbcProducts-EP-805.xml")
    //    val rootNode = XML.loadFile("responses/payloads/2.AbcProducts-EP-905F.xml")
    val rootNode = XML.loadFile("responses/payloads/raw/3.Accessories-KL50SCKR.xml")

    println("-" * 64)

    // SAX Parsing by default.

    //@tailrec
    def processNode(node: Node) {
      if (node.isInstanceOf[Text]) {
      } else {
        println(node.label)
      }
      node.child.foreach { e => { processNode(e) } }
    }

    processNode(rootNode)

  }
}
