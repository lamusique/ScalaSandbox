package com.nekopiano.scala.di.compiletime

import com.nekopiano.scala.di.compiletime.components.{ServiceModuleATrait, ServiceModuleBTrait}


object SingletonInjectedApp extends App with ServiceModuleATrait with ServiceModuleBTrait {

  println(serviceA.repo)
  //  com.nekopiano.scala.di.compiletime.components.DefoRepo@3f3afe78
  println(serviceB.repo)
  //  com.nekopiano.scala.di.compiletime.components.DefoRepo@3f3afe78

}
