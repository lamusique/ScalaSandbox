package com.nekopiano.scala.di.compiletime.cakepattern

import com.nekopiano.scala.di.compiletime.cakepattern.components.{ServiceModuleATrait, ServiceModuleBTrait}

object SingletonInjectedApp extends App with ServiceModuleATrait with ServiceModuleBTrait {

  println(serviceA.repo)
  //  DefoRepo@3f3afe78
  println(serviceB.repo)
  //  DefoRepo@3f3afe78

}
