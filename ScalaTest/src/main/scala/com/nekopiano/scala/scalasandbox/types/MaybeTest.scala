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

  val n = null
  val o = Option(n)
  println("o=" + o)
  // converted to None

  val optionalNull = getOptionNull()
  println("optionalNull=" + optionalNull)
  // null still
  val optionalNone = getOptionNone()
  println("optionalNone=" + optionalNone)
  // converted to None
  //println("optionalNone.get=" + optionalNone.get)
  //java.util.NoSuchElementException: None.get
  println("optionalNone.getOrElse=" + optionalNone.getOrElse(null))

  def getOptionNull(): Option[String] = if (false) Some("") else null
  def getOptionNone(): Option[String] = if (false) Some("") else None

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

