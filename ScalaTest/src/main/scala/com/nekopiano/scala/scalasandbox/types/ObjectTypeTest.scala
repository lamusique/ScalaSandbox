/**
 *
 */
package com.nekopiano.scala.scalasandbox.types

/**
 * @author La Musique
 *
 */
object ObjectTypeTest extends App {

  val foo = new Foo
  receiveObject(foo)

  //receiveObject(Foo)
  //type mismatch; found : com.nekopiano.scala.scalasandbox.types.Foo.type required: com.nekopiano.scala.scalasandbox.types.Foo

  //receiveObject(Foo.instance)
  //type mismatch; found : com.nekopiano.scala.scalasandbox.types.Foo.type required: com.nekopiano.scala.scalasandbox.types.Foo

  def receiveObject(foo: Foo) = {}

  def receiveObject(bar: BarTrait) = {}
  val b = Bar
  receiveObject(b)

}

// ==== with a Trait

class Foo {}
object Foo { def instance = this }

trait BarTrait
object Bar extends BarTrait

// ==== with parameter

class ParameterA
trait ParametricA[_ <: ParameterA] {}

//object ParametricB[P<: ParameterA] extends ParametricA[P]
//';' expected but '[' found.

//object ParametricB extends ParametricA[_ <: ParameterA]
//class type required but com.nekopiano.scala.scalasandbox.types.ParametricA[_ <: com.nekopiano.scala.scalasandbox.types.ParameterA] found
object ParametricB extends ParametricA[ParameterA]

class ParametricC(parametric: ParameterA) extends ParametricA[ParameterA]



