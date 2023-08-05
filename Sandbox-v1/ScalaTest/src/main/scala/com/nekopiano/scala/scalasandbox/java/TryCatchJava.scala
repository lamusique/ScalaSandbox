/**
 *
 */
package com.nekopiano.scala.scalasandbox.java

/**
 * @author La Musique
 *
 */
//object TryCatchJava extends App {
object TryCatchJava {

  //  util.Properties.setProp("scala.time", "")

  def main(args: Array[String]): Unit = {

    val result = try {
      val str = "a"
      val nullStr = if (str == "a") {
        null
      } else {
        "b"
      }
      nullStr.length
    } catch {
      case e: NullPointerException => println("this is null.")
    }

    println("result.getClass=" + result.getClass)
  }

}
