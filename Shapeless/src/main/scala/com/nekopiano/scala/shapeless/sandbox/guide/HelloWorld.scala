package com.nekopiano.scala.shapeless.sandbox.guide

import shapeless._

case class Greeting(message: String)
case class GreetingB(msg: String, date: java.util.Date)

object HelloWorld extends App {

  val generic = Generic[Greeting]

  val hlist: String :: HNil =
    "Hello from shapeless!" :: HNil

  val greeting: Greeting =
    generic.from(hlist)

  println(greeting.message)


  val genericB = Generic[GreetingB]

  val hlistB: String :: java.util.Date :: HNil =
    "Hello from shapeless!" :: new java.util.Date :: HNil

  val greetingB: GreetingB = genericB.from(hlistB)

  println(greetingB.msg + greetingB.date)


}
