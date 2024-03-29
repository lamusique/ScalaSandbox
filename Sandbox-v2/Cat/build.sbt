name := "Cat-Sandbox"
version := "0.1.0"
organization := "com.nekopiano.scala"
scalaVersion := "2.13.0"


libraryDependencies += "com.lihaoyi" %% "sourcecode" % "0.1.4"


// SI-2712, the compiler to unify type constructors of different arities.
//scalacOptions += "-Ypartial-unification"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0-RC1"

libraryDependencies += "org.typelevel" %% "cats-mtl-core" % "0.6.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "2.0.0-RC1"
libraryDependencies += "org.typelevel" %% "mouse" % "0.22"
libraryDependencies += "org.typelevel" %% "kittens" % "2.0.0-RC1"

addCompilerPlugin(
  ("org.scalameta" % "paradise" % "3.0.0-M11").cross(CrossVersion.full))
libraryDependencies +=
  "org.typelevel" %% "cats-tagless-macros" % "0.9"

