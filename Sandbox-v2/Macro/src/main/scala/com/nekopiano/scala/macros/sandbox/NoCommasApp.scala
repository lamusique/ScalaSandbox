package com.nekopiano.scala.macros.sandbox

object NoCommasApp extends App {
  lazy val a = 1
  lazy val b = 2
  lazy val c = 3

  import NoCommas.nocommas

  val listSymblique = nocommas {
    a
    b
    c
  }
  println(listSymblique)

  val listLiteral = nocommas {
    10
    11
    12
  }
  println(listLiteral)

//  val listLikeLisp = nocommas {
//    1 2 3
//  }
//  println(listLikeLisp)

}
