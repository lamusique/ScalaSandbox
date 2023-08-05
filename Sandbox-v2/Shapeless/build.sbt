name := "Shapeless-Sandbox"
version := "0.1.0"
organization := "com.nekopiano.scala"
scalaVersion := "2.12.6"

libraryDependencies += "com.lihaoyi" %% "sourcecode" % "0.1.4"

libraryDependencies += "com.chuusai" %% "shapeless" % "2.3.3"


// a workaround due to provided in pom.xml of shapeless.
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.6"

