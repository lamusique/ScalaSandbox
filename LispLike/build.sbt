////import Dependencies._
//
//seq(clojure.settings :_*)
////clojure.settings
//
//lazy val root = (project in file(".")).
//  settings(
//    inThisBuild(List(
//      organization := "com.nekopiano.scala",
//      //scalaVersion := "2.12.8",
//      scalaVersion := "2.10.7",
//      version      := "0.1.0-SNAPSHOT"
//    )),
//    name := "lisp-like",
//    libraryDependencies ++= Seq(
////      clojureJar,
////      scalaTestJar % Test
//       "org.clojure" % "clojure" % "1.5.1",
//      "org.scalatest" %% "scalatest" % "3.0.5"
//      
//    )
//  )

seq(clojure.settings :_*)

      organization := "com.nekopiano.scala"

      version      := "0.1.0-SNAPSHOT"

libraryDependencies += "org.clojure" % "clojure" % "1.5.1"

