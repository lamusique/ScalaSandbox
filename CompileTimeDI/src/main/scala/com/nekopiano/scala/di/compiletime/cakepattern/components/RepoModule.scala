package com.nekopiano.scala.di.compiletime.cakepattern.components

import com.softwaremill.macwire._

trait RepoModule {

  lazy val repo: Repo = wire[DefoRepo]

}
