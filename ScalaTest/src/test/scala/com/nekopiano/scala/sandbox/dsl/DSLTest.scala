/**
 *
 */
package com.nekopiano.scala.sandbox.dsl

/**
 * @author La Musique
 *
 */
object DSLTest {

  def main(args: Array[String]): Unit = {

    // this is actual int primitive type as Java.
    val testInt = 11

    val 一 = new 数字(1)
    val 三 = new 数字(3)
    val answer = 一 足す 三
    println("answer=" + answer)

    implicit def int足す(int: Int) = new { def 足す(intRight: Int) = int + intRight }

    val answer2 = 1 足す 3
    println("answer2=" + answer2)

  }

  class 数字(val number: Int) {
    def 足す(that: 数字): 数字 = new 数字(this.number + that.number)

    override def toString() = {
      this.number.toString
    }
  }

}

