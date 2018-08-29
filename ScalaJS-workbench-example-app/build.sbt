
enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

name := "Example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.6",
  "com.lihaoyi" %%% "scalatags" % "0.6.7",
  "com.lihaoyi" %%% "sourcecode" % "0.1.4",
  "org.scala-lang.modules" %%% "scala-parser-combinators" % "1.1.1"
)

//libraryDependencies += "com.lihaoyi" %% "sourcecode" % "0.1.4"
//libraryDependencies += "com.ibm.icu" %%% "icu4j" % "62.1"

libraryDependencies += "com.lihaoyi" %%% "utest" % "0.6.3" % "test"
testFrameworks += new TestFramework("utest.runner.Framework")

artifactPath in fastOptJS :=
  ((crossTarget in fastOptJS).value /
    ("neko-fastopt.js"))

artifactPath in fullOptJS :=
  ((crossTarget in fullOptJS).value /
    ("neko-opt.js"))

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

