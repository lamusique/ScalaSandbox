
organization := "com.nekopiano.scala"

name := "scala-sandbox"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= List(
/*
    "org.scala-lang" % "scala-reflect" % "2.10.4",
    "com.typesafe" % "config" % "1.2.0",
    "com.github.nscala-time" %% "nscala-time" % "1.2.0",
    "joda-time" % "joda-time" % "2.3",
    "org.joda" % "joda-convert" % "1.6",
    "commons-cli" % "commons-cli" % "1.2",
    "pl.project13.scala" %% "rainbow" % "0.2",
    "org.fusesource.jansi" % "jansi" % "1.11",
    "org.specs2" %% "specs2" % "2.3.12" % "test"
*/
)


scalacOptions in Test ++= Seq("-Yrangepos")


resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)


unmanagedBase := baseDirectory.value / "lib"


// Not for IntelliJ but for Eclipse
//EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
//
//EclipseKeys.withSource := true

//packageOptions := Seq(ManifestAttributes(
//                      ("Implementation-Vendor", "vendor"),
//                      ("Implementation-Title", "title")))

mainClass in assembly := Some("com.nekopiano.scala.MainExecutor")

test in assembly := {}

