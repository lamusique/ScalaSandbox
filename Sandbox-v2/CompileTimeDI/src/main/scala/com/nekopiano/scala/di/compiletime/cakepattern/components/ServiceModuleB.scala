package com.nekopiano.scala.di.compiletime.cakepattern.components

import com.softwaremill.macwire._

class ServiceModuleBClass extends RepoModule {
  lazy val serviceB: Service = wire[FixedService]
}
trait ServiceModuleBTrait extends RepoModule {
  lazy val serviceB: Service = wire[FixedService]
}
