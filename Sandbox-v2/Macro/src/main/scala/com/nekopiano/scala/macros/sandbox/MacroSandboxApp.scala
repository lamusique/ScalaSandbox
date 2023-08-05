package com.nekopiano.scala.macros.sandbox

import language.experimental.macros

object MacroSandboxApp extends App {

  val s = MacroSandbox.desugar(List(1, 2, 3).reverse)
  println(s)
  //scala.collection.immutable.List.apply[Int](1, 2, 3).reverse

}
