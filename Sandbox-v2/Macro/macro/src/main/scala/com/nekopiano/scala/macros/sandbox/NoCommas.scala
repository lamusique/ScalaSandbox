package com.nekopiano.scala.macros.sandbox

object NoCommas {
  import scala.language.experimental.macros
  import scala.reflect.macros.blackbox.Context

    def nocommas[A](a: A): Vector[A] = macro nocommasImpl[A]

    def nocommasImpl[A: c.WeakTypeTag](c: Context)(a: c.Expr[A]) : c.Expr[Vector[A]] = {
      import c.universe._
      val items: List[Tree] = a.tree match {
        case Block(stats, x) => stats ::: List(x)
        case x               => List(x)
      }
      c.Expr[Vector[A]](
        Apply(Select(reify(Vector).tree, TermName("apply")), items))
    }
}
