package com.nekopiano.scala.sandbox.test;

import org.specs2.mutable._
import org.specs2.specification.BeforeAfterExample
import org.specs2.specification.Example
import org.specs2.specification.ExampleFactory
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Specs2NameTest extends Specification {
  override def is =
    "testabc" ! {
      ok
    }
  case class BeforeAfterExample(e: Example) extends BeforeAfter {
    def before = println("before " + e.desc)
    def after = println("after " + e.desc)
  }
  override def exampleFactory = new ExampleFactory {
    def newExample(e: Example) = {
      val context = BeforeAfterExample(e)
      e.copy(body = () => context(e.body()))
    }
  }
}
