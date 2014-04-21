/**
 *
 */
package com.nekopiano.scala.scalasandbox.types

/**
 * @author La Musique
 *
 */
object ExtendCompanionTest {
  
  val pcc = PlainCaseClass("test")
  val a = PlainCaseClass.apply("a")

  
}

case class PlainCaseClass(arg:String) {}
object PlainCaseClass {
  val constant = "xxx"
}
