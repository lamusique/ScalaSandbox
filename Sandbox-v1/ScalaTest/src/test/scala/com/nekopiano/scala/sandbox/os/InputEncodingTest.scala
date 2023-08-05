/**
 *
 */
package com.nekopiano.scala.sandbox.os

import java.util.Scanner

/**
 * @author La Musique
 *
 */
object InputEncodingTest {

  def main(args: Array[String]): Unit = {
    // val scan = new Scanner(System.in, "UTF8")
    val scan = new Scanner(System.in)
    System.out.println("Please enter a string.")
    val input = scan.nextLine
    print(input)
    def print(input: String) {
      println("input=" + input)
      println("%04X".format(input.charAt(0).toInt))
      println("UTF8=" + input.getBytes("UTF8").mkString)
      println("MS932=" + input.getBytes("MS932").mkString)
      println("UTF16=" + input.getBytes("UTF16").mkString)
    }
    //print("あ")

  }

}
