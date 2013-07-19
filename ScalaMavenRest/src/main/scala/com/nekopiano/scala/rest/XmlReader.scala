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
object XmlReader {

  def main(args: Array[String]): Unit = {

    //    val xmlSource = Source.fromFile("responses/payloads/2.AbcProducts-EP-805.xml")
    //    val rootNode = toNode(xmlSource.mkString)
    //    val rootNode = XML.loadFile("responses/payloads/2.AbcProducts-EP-805.xml")
    val rootNode = XML.loadFile("responses/payloads/1.ApprovedList.xml")

    //rootNode.child.map(node => println(node))

    println("-" * 64)

    //    val doc = ConstructingParser.fromFile(new File("responses/payloads/2.AbcProducts-EP-805.xml"), true).document

    val dbFactory = DocumentBuilderFactory.newInstance;
    val dBuilder = dbFactory.newDocumentBuilder;
    val domDoc = dBuilder.parse(new File("responses/payloads/2.AbcProducts-EP-805.xml"));

    val domNode = nodeExtras(rootNode)
    val jdkNode = domNode.toJdkNode(domDoc)
    println("jdkNode=" + jdkNode)
    println("jdkNode.getChildNodes=" + jdkNode.getParentNode())
    jdkNode.getChildNodes()

    println("-" * 64)

    //    @tailrec
    def traceDom(jdkNode: org.w3c.dom.Node): Unit = {
      val children = jdkNode.getChildNodes();
      if (children.getLength() == 0) {
        // This node is a terminal.
        @tailrec
        def traceParents(parents: List[org.w3c.dom.Node], jdkNode: org.w3c.dom.Node): Unit = {
          val parentNode = jdkNode.getParentNode()
          if (parentNode == null) {
            //            println("parents.length=" + parents.length)
            parents.foreach(parent => {
              print(parent.getNodeName)
              print("//")
            })
            println

          } else {
            //            println("jkdNode=" + jdkNode + ", parentNode=" + parentNode)
            traceParents(parentNode :: parents, parentNode)
          }
        }
        traceParents(jdkNode :: List.empty, jdkNode)

      } else {
        //        print("jdkNode=" + jdkNode)
        //        println(", children=" + children)
        for (i <- 0 to children.getLength) {
          val child = children.item(i)
          if (child == null) {
            //why get null?
          } else {
            traceDom(child)
          }
        }
      }
    }
    traceDom(jdkNode)

    return

    println("-" * 64)

    //@tailrec
    def processNode(node: Node) {
      //      if (node.isInstanceOf[Text]) {
      //      println(node.doCollectNamespaces)
      //      println(node.namespace)
      if (node.child.isEmpty) {
        println(node.label)
      } else {
        print(node.label + " // ")
      }
      //     }
      node.child.foreach { e => { processNode(e) } }
    }

    processNode(rootNode)

  }

  def toNode(str: String): Node = {
    val hp = new HtmlParser
    hp.setNamePolicy(XmlViolationPolicy.ALLOW)

    val saxer = new NoBindingFactoryAdapter
    hp.setContentHandler(saxer)
    hp.parse(new InputSource(new StringReader(str)))

    saxer.rootElem
  }

  object XmlHelpers {
    val docBuilder =
      javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder()
  }

  class NodeExtras(n: Node) {
    def toJdkNode(doc: org.w3c.dom.Document): org.w3c.dom.Node =
      n match {
        case Elem(prefix, label, attributes, scope, children @ _*) =>
          // XXX: ns
          val r = doc.createElement(label)
          for (a <- attributes) {
            r.setAttribute(a.key, a.value.text)
          }
          for (c <- children) {
            r.appendChild(c.toJdkNode(doc))
          }
          r
        case Text(text) => doc.createTextNode(text)
        case Comment(comment) => doc.createComment(comment)
        // not sure
        case a: Atom[_] => doc.createTextNode(a.data.toString)
        // XXX: other types
        //case x => throw new Exception(x.getClass.getName)
      }
  }

  class ElemExtras(e: Elem) extends NodeExtras(e) {
    override def toJdkNode(doc: org.w3c.dom.Document) =
      super.toJdkNode(doc).asInstanceOf[org.w3c.dom.Element]

    def toJdkDoc = {
      val doc = XmlHelpers.docBuilder.newDocument()
      doc.appendChild(toJdkNode(doc))
      doc
    }
  }

  implicit def nodeExtras(n: Node) = new NodeExtras(n)
  implicit def elemExtras(e: Elem) = new ElemExtras(e)

}
