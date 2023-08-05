/**
 *
 */
package com.nekopiano.scala.scalasandbox.types

/**
 * @author La Musique
 *
 */
object NumberConversion extends App {

  val d = 1.999
  val bd = BigDecimal("1.9999")

  println("d=" + d / 9)
  println("bd=" + bd / 9)

  println("d.toInt=" + d.toInt)
  println("bd.toInt=" + bd.toInt)
}
