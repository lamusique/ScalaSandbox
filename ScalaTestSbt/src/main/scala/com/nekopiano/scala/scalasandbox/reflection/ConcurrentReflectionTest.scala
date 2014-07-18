/**
 *
 */
package com.nekopiano.scala.scalasandbox.reflection

import scala.reflect.macros.Context
import scala.language.experimental.macros

/**
 * @author La Musique
 *
 */
object ConcurrentReflectionTest extends App {

  // NOTES: This can't be concurrent.
  // https://issues.scala-lang.org/browse/SI-6240
  def entity2mapOnRuntime(entities: Object) = synchronized {
    import reflect.runtime.universe._
    import reflect.runtime.currentMirror

    val r = currentMirror.reflect(entities)
    r.symbol.typeSignature.members.toStream
      .collect { case s: TermSymbol if !s.isMethod => r.reflectField(s) }
      .map(r => r.symbol.name.toString.trim -> r.get)
      .toMap
  }

  val foos = List(Foo("1", 1), Foo("2", 2), Foo("3", 3), Foo("4", 4), Foo("5", 5), Foo("6", 6), Foo("7", 7))

  //  val mapped = foos.par map (foo => {
  //    // a certain error occurs due to the concurrency problem
  //    foo.entity2mapOnRuntime()
  //  })
  //  println("mapped=" + mapped)

  val entity2mapOnRuntimeMapped = foos.par map (foo => {
    // a certain error occurs due to the concurrency problem
    entity2mapOnRuntime(foo)
  })
  println("entity2mapOnRuntimeMapped=" + entity2mapOnRuntimeMapped)
  // works because of object method.

  // a certain error occurs due to the concurrency problem
  //MacroObject.macroMethod
  // compile error
  //macro implementation not found: macroMethod (the most common reason for that is that you cannot use macro implementations in the same compilation run that defines them)

}

case class Foo(a: String, b: Int) {

  def entity2mapOnRuntime() = synchronized {
    import reflect.runtime.universe._
    import reflect.runtime.currentMirror

    val r = currentMirror.reflect(this)
    r.symbol.typeSignature.members.toStream
      .collect { case s: TermSymbol if !s.isMethod => r.reflectField(s) }
      .map(r => r.symbol.name.toString.trim -> r.get)
      .toMap
  }
  def entity2mapOnCompileTime() = synchronized {
    //    import scala.reflect.macros.Context.universe._

  }
  import scala.reflect.macros.Context

  //def macro: Foo = macro impl
  // macro can't be placed in class but object.

}

object MacroObject {

  def macroMethod: Foo = macro impl

  def impl(c: Context): c.Expr[Foo] = {
    import c.universe._
    val pos = c.macroApplication.pos
    val clsLocation = c.mirror.staticModule("Location") // get symbol of "Location" object
    c.Expr(Apply(Ident(clsLocation), List(Literal(Constant(pos.source.path)), Literal(Constant(pos.line)), Literal(Constant(pos.column)))))
  }

}

