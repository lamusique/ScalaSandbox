package com.nekopiano.scala.di.compiletime.cakepattern.components

import com.softwaremill.macwire._

class ServiceModuleAClass extends RepoModule {
  lazy val serviceA: Service = wire[TimeService]
}
trait ServiceModuleATrait extends RepoModule {
  lazy val serviceA: Service = wire[TimeService]
}
