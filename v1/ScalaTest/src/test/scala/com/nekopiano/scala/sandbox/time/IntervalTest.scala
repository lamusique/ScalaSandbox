/**
 *
 */
package com.nekopiano.scala.sandbox.time

/**
 * @author La Musique
 *
 */
object IntervalTest extends App {

  {
    import org.joda.time._

    val past = new DateTime("2010-04-17T16:53:08Z")
    println("past=" + past)
    val future = new DateTime("2020-05-28T17:54:09Z")
    println("future=" + future)

    val rightInterval = new Interval(past, future)
    println("rightInterval=" + rightInterval)

    // val reverseInterval = new Interval(future, past)
    // Exception in thread "main" java.lang.IllegalArgumentException: The end instant must be greater or equal to the start
    // println("reverseInterval=" + reverseInterval)

    val noInterval = new Interval(past, past)
    println("noInterval=" + noInterval)
    //2010-04-18T01:53:08.000+09:00/2010-04-18T01:53:08.000+09:00
    println("noInterval.toDurationMillis()=" + noInterval.toDurationMillis())
    //0
  }

  {
    import com.github.nscala_time.time.Imports._
    val pastNScala = DateTime.+("2010-04-17T16:53:08Z")
    println("pastNScala=" + pastNScala)
    //com.github.nscala_time.time.StaticDateTime$@5f66320b2010-04-17T16:53:08Z
  }

}
