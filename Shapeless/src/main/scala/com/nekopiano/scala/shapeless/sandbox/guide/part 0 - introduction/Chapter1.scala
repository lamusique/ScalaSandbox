package com.nekopiano.scala.shapeless.sandbox.guide.ch00

object Chapter1 extends App {

  import com.nekopiano.scala.utilities.CodeUtility._


  case class Employee(name: String, number: Int, manager: Boolean)
  case class IceCream(name: String, numCherries: Int, inCone: Boolean)


  def employeeCsv(e: Employee): List[String] =
    List(e.name, e.number.toString, e.manager.toString)
  def iceCreamCsv(c: IceCream): List[String] =
    List(c.name, c.numCherries.toString, c.inCone.toString)



  import shapeless._

  val genericEmployee = Generic[Employee].to(Employee("Dave", 123, false))
  // genericEmployee: shapeless.::[String,shapeless.::[Int,shapeless.::[Boolean,shapeless.HNil]]] = Dave :: 123 :: false :: HNil
  val genericIceCream = Generic[IceCream].to(IceCream("Sundae", 1, false))
  // genericIceCream: shapeless.::[String,shapeless.::[Int,shapeless.::[Boolean,shapeless.HNil]]] = Sundae :: 1 :: false :: HNil

  // caveat: ithout scala-reflect this would fail.
  def genericCsv(gen: String :: Int :: Boolean :: HNil): List[String] =
    List(gen(0).toString, gen(1).toString, gen(2).toString)

  println(inspect(genericEmployee(1)))

  val strlist01 = genericCsv(genericEmployee)
  println(inspect(strlist01))
  // res2: List[String] = List(Dave, 123, false)
  val strlist02 = genericCsv(genericIceCream)
  println(inspect(strlist02))
  // res3: List[String] = List(Sundae, 1, false)




}
