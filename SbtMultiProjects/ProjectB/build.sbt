
ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val projecta = (project in file("../ProjectA"))
lazy val projectb = (project in file("."))
  .settings(
    name := "projectb"
  ).dependsOn(projecta)
// Removing dependsOn causes a compilaton error.

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
