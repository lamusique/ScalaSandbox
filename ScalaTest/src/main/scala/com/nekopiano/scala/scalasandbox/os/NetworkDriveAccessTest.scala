/**
 *
 */
package jp.sample.scala.os

import scala.io.Source

/**
 * @author La Musique
 *
 */
object ServerAccessTest {

  def main(args: Array[String]): Unit = {
    val fileURL = Source.fromURL("file://172.16.100.110/share/test.txt", "UTF-8")
    println(fileURL.mkString)
    val fileFile = Source.fromFile("\\\\172.16.100.110\\share\\test.txt", "UTF-8")
    println(fileFile.mkString)
  }

}
