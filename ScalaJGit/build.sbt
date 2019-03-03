import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.nekopiano"
ThisBuild / organizationName := "Neko Piano"

lazy val root = (project in file("."))
  .settings(
    name := "Scala JGit",
    normalizedName := "scala-jgit",
    libraryDependencies += scalaTest % Test
  )

