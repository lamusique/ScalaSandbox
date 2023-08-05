/**
 *
 */
package com.nekopiano.scala.scalasandbox.types

/**
 * @author La Musique
 *
 */
object ExtendCompanionTest {

  val pcc = OneArgCaseClass("test")
  val a = OneArgCaseClass.apply("a")

  //  val tupledOne = OneArgCaseClass.tupled
  // compile error owing to one arg

  val tupledTwo = TwoArgsCaseClass.tupled

  case class Person(name: String, age: Int)
  // def apply(name: String, age: Int): Personを
  // def f(tuple: (String, Int)): Personのように変換する
  val f = Person.tupled
  val tuple = ("Taro", 30)
  f(tuple)

}

case class OneArgCaseClass(arg: String) {}
//object PlainCaseClass {
//  val constant = "xxx"
//}
case class TwoArgsCaseClass(arg1: String, arg2: String) {}
// object TwoArgsCaseClass {}
// makes TwoArgsCaseClass.tupled have a compile error.
