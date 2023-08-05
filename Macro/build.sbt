
name := "macro-sadbox"
version in Global := "1.0"
scalaVersion in Global := "2.12.8"


lazy val root = (project in file(".")).
  aggregate(macroProject).
  dependsOn(macroProject).
  settings(
    mappings in (Compile, packageBin) ++= mappings.in(macroProject, Compile, packageBin).value,
    mappings in (Compile, packageSrc) ++= mappings.in(macroProject, Compile, packageSrc).value
  )

lazy val macroProject = (project in file("macro")).
  settings(
    scalacOptions += "-language:experimental.macros",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
  )


libraryDependencies in Global ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

fork in Global := true
crossPaths in Global := false

