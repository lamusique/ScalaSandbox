package com.nekopiano.scala.macros.sandbox

//import language.experimental.macros
//import scala.reflect.macros.blackbox.Context
import scala.reflect.macros.whitebox.Context

object MacroSandbox {

  def desugar(a: Any): String = macro desugarImpl

  def desugarImpl(c: Context)(a: c.Expr[Any]) = {
    import c.universe._

    val s = show(a.tree)
    c.Expr(
      Literal(Constant(s))
    )
  }

}
