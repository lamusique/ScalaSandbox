/**
 *
 */
package com.nekopiano.scala.scalasandbox.types

/**
 * @author La Musique
 *
 */
object CompanionTest extends App {

  val c = classOf[CaseClass]
  println("c = " + c)
  //c=class com.nekopiano.scala.scalasandbox.types.CaseClass
  val t = scala.reflect.runtime.universe.typeOf[CaseClass]
  println("t = " + t)
  //t=com.nekopiano.scala.scalasandbox.types.CaseClass
  val caseClassTypeTag = scala.reflect.runtime.universe.typeTag[CaseClass]
  println("caseClassTypeTag = " + caseClassTypeTag)

  
  CaseClass.paramInfo(12345)

  
  val cc = CaseClass
  println("cc.getClass = " + cc.getClass)
  //cc.getClass=class com.nekopiano.scala.scalasandbox.types.CaseClass$
  val caseClassName = cc.getClass.getName.dropRight(1)
  println("caseClassName = " + caseClassName)
  val caseClassClass = Class.forName(caseClassName)
  println("caseClassClass = " + caseClassClass)

  //  println("cc=" + classOf[cc]) <= compile error
  val tagType = scala.reflect.runtime.universe.typeTag[Int].tpe
  //val someType = classOf[tagType] <= compile error

  println("cc = " + scala.reflect.runtime.universe.typeOf[cc.type])
  //cc=com.nekopiano.scala.scalasandbox.types.CompanionTest.cc.type

}

case class CaseClass(code: String) {}
object CaseClass {
  import scala.reflect.runtime.universe._
  def paramInfo[T](x: T)(implicit tag: TypeTag[T]): Unit = {
    println("tag = " + tag)
    println("tag.tpe = " + tag.tpe)
    val targs = tag.tpe match { case TypeRef(_, _, args) => args }
    println(s"type of $x has type arguments $targs")
  }
}
