package com.nekopiano.scala.di.compiletime

import com.softwaremill.macwire._
import com.nekopiano.scala.di.compiletime.components.{ServiceModuleAClass, ServiceModuleBClass}


object NotSingletonInjectedApp extends App {

  val a = wire[ServiceModuleAClass]
  val b = wire[ServiceModuleBClass]

  println(a.repo)
  //  com.nekopiano.scala.di.compiletime.components.DefoRepo@3f3afe78
  println(b.repo)
  //  com.nekopiano.scala.di.compiletime.components.DefoRepo@7f63425a

}
