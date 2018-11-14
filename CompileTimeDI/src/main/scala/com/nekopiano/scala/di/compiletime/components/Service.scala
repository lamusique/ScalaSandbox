package com.nekopiano.scala.di.compiletime.components

import com.softwaremill.macwire._
import java.util.Date

trait Service {
  val repo: Repo
  def run: String
}

class TimeService(override val repo: Repo) extends Service {
  override def run: String = new Date().toString
}

class FixedService(override val repo: Repo) extends Service {
  override def run: String = "fixed"
}
