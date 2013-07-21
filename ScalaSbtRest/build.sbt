name := "ScalaSbtRestClient"

organization := "$organization$"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "1.13" % "test",
  "nu.validator.htmlparser" % "htmlparser" % "1.4"
)

//initialCommands := "import $organization$.$name;format="lower,word"$._"
