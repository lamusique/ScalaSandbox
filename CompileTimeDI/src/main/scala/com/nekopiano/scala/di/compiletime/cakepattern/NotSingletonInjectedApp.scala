package com.nekopiano.scala.di.compiletime.cakepattern

import com.nekopiano.scala.di.compiletime.cakepattern.components.{ServiceModuleAClass, ServiceModuleBClass}
import com.softwaremill.macwire.wire

object NotSingletonInjectedApp extends App {

  val a = wire[ServiceModuleAClass]
  val b = wire[ServiceModuleBClass]

  println(a.repo)
  //  DefoRepo@3f3afe78
  println(b.repo)
  //  DefoRepo@7f63425a

}
