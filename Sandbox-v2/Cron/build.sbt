

name := "Cron"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "org.quartz-scheduler" % "quartz" % "2.3.0",
  "com.lihaoyi" %% "sourcecode" % "0.1.4"
)


libraryDependencies += "com.enragedginger" %% "akka-quartz-scheduler" % "1.6.1-akka-2.5.x"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.16"


