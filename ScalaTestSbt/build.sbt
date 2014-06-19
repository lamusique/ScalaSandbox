import AssemblyKeys._
//    import sbt._
//    import Keys._
//    import sbt.Package.ManifestAttributes

assemblySettings

organization := "com.nekopiano.scala"

name := "sbt-skeleton"

version := "1.0"

scalaVersion := "2.11.1"

libraryDependencies ++= List(
  "org.scala-lang" % "scala-reflect" % "2.10.4",
//  "com.typesafe.slick" %% "slick" % "2.0.1",
//  "com.typesafe.slick" %% "slick-extensions" % "2.0.1",
  "com.typesafe" % "config" % "1.2.0",
//  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.slf4j" % "slf4j-log4j12" % "1.7.6",
  "log4j" % "apache-log4j-extras" % "1.2.17",
  "org.bgee.log4jdbc-log4j2" % "log4jdbc-log4j2-jdbc4" % "1.16",
  "mysql" % "mysql-connector-java" % "5.1.23",
  "org.apache.commons" % "commons-dbcp2" % "2.0",
//  "com.h2database" % "h2" % "1.3.170",
//  "org.xerial" % "sqlite-jdbc" % "3.7.2"
    "com.github.nscala-time" %% "nscala-time" % "1.2.0",
    "joda-time" % "joda-time" % "2.3",
    "org.joda" % "joda-convert" % "1.6",
    "commons-cli" % "commons-cli" % "1.2",
    "pl.project13.scala" %% "rainbow" % "0.2",
    "org.fusesource.jansi" % "jansi" % "1.11",
    "org.specs2" %% "specs2" % "2.3.12" % "test"
)


scalacOptions in Test ++= Seq("-Yrangepos")


resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)


unmanagedBase := baseDirectory.value / "lib"


EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.withSource := true

//packageOptions := Seq(ManifestAttributes(
//                      ("Implementation-Vendor", "vendor"),
//                      ("Implementation-Title", "title")))

mainClass in assembly := Some("com.nekopiano.scala.MainExecutor")

test in assembly := {}

