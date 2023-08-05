import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.nekopiano"
ThisBuild / organizationName := "Neko Piano"

lazy val root = (project in file("."))
  .settings(
    name := "stream-downloader",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.1.7",
      "com.typesafe.akka" %% "akka-http-testkit" % "10.1.7" % Test
    ),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % "2.5.21",
      "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.21" % Test
    ),
    libraryDependencies += scalaTest % Test

  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
