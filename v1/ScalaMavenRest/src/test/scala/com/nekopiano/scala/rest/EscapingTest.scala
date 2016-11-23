/**
 *
 */
package com.nekopiano.scala.rest

/**
 * @author La Musique
 *
 */
object EscapingTest {

  def main(args: Array[String]): Unit = {

    val htmlSample = "<a href=\"http://test/\">ここは \'リンク\'&です。</a>"
    val xmlSample = ""

    println(xml.Utility.escape(htmlSample))

  }

}
