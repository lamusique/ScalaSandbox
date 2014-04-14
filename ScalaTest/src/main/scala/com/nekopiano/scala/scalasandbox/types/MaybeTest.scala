/**
 *
 */
package com.nekopiano.scala.scalasandbox.types

/**
 * @author La Musique
 *
 */
object MaybeTest extends App {
  util.Properties.setProp("scala.time", "MaybeTest")

  matchOption(None)
  matchOption(Option("test"))
  matchOption(null)

  // matchOptionWithoutDefault(null)
  // Exception in thread "main" scala.MatchError: null

  def matchOption(value: Option[String]) = {
    println(s"value=$value")
    value match {
      case Some(str) => println(s"str=$str")
      case None => println("here is None")
      case _ => println("here is _")
    }
  }

  def matchOptionWithoutDefault(value: Option[String]) = {
    println(s"value=$value")
    value match {
      case Some(str) => println(s"str=$str")
      case None => println("here is None")
    }
  }

  val opt = Option("a")
  val wrapped = Option(opt)
  println("wrapped.get=" + wrapped.get)

  // Thus
  // A sender object should send bare values, e.g. Int, String, because they may be null.
  // A receiver object should receive them and wraps them by Option, then processes.
  
}

