/**
 *
 */
package com.nekopiano.scala.scalasandbox.random

/**
 * @author La Musique
 *
 */
object RadomString extends App {

  val rnd = new scala.util.Random

  println(rnd.nextString(1))
  println(rnd.nextString(2))
  println(rnd.nextString(3))
  println(rnd.nextString(10))
  println(rnd.nextString(50))

}
