package com.nekopiano.scala.di.compiletime.cakepattern.components

trait Repo {
  def get: String
}

class DefoRepo extends Repo {
  override def get: String = "text"
}
