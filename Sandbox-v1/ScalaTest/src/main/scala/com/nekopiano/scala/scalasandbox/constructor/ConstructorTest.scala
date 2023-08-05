/**
 *
 */
package com.nekopiano.scala.scalasandbox.constructor

/**
 * @author La Musique
 *
 */
object ConstructorTest extends App {

  val t = new TimeSet()
  println("t.milliTime=" + t.milliTime)
  val t2 = new TimeSet()
  println("t2.milliTime=" + t2.milliTime)
  // vals of them differ.

  class TimeSet() {
    // insert
    val milliTime = System.currentTimeMillis
    val now = new java.sql.Timestamp(milliTime)
    println("now=" + now)
    // 2014-07-10 14:56:28.149
    val past = new java.sql.Timestamp(milliTime - 500000000L)
    println("past=" + past)
    // 2014-11-03 08:43:08.149
    val future = new java.sql.Timestamp(milliTime + 5000000000L)
    println("future=" + future)
    // 2014-11-03 08:43:08.149
    val fartherFuture = new java.sql.Timestamp(milliTime + 10000000000L)
    // 2014-11-03 08:43:08.149
    println("fartherFuture=" + fartherFuture)
    val remoterPast = new java.sql.Timestamp(milliTime - 10000000000L)
    // 2014-03-16 21:09:48.149
    println("remoterPast=" + remoterPast)
  }

}
