/**
 *
 */
package com.nekopiano.scala.xml

import scala.xml._

/**
 * @author La Musique
 *
 */
object XmlElementSorter {

  // shouldn't write:
  // <?xml version="1.0" encoding="UTF-8"?>
  val unsortedXml =
    <a>
      <bb attr="b">D</bb>
      <aa attr="a">A</aa>
      <cc attr="c">C</cc>
      <dd attr="b">D</dd>
      <dd attr="a">A</dd>
      <dd attr="c">C</dd>
    </a>

  def main(args: Array[String]): Unit = {

    val sortTargetElements = unsortedXml \\ "dd"
    println("sortTargetElements=" + sortTargetElements)
    //    sortTargetElements.sortWith(
    //      _.getAttribute("").compareToIgnoreCase(_.getAttribute("")) < 0)
    val sorted = sortTargetElements.sortBy(s => (s \ "@attr").text)
    println("sorted=" + sorted)
  }

}
