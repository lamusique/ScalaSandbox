package com.nekopiano.scala.sandbox.test;

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith
import org.specs2.specification.Example
import org.specs2.specification.ExampleFactory
import org.specs2.specification.BeforeAfterExample

@RunWith(classOf[JUnitRunner])
class Specs2Test extends Specification with BeforeAfterExample {

  var currentExample = 0
  var testName = ""

  "The 'Hello world' string" should {
    "contain 11 characters" in {
      "Hello world" must have size (11)
    }
    "start with 'Hello'" in {
      "Hello world" must startWith("Hello")
    }
    "end with 'world'" in {
      "Hello world" must endWith("world")
    }
  }

  "Test name" should {
    "by example" in {
      val testName = is.examples(0).desc.toString.replaceAll(" ", "-")
      println("testName=" + testName)
      true must beTrue
    }
    "by thread" in {
      val st = Thread.currentThread.getStackTrace.asInstanceOf[Array[StackTraceElement]]
      println("st=" + st(1))
      true must beTrue
    }
  }

  def before {
    testName = is.examples(currentExample).desc.toString.replaceAll(" ", "-")
      println("before testName=" + testName)
  }

  def after {
    currentExample += 1
  }
}
