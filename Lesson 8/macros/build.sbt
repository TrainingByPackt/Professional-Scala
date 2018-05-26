name := "Lesson3-Macros"

scalaVersion := "2.12.0"

lazy val macros = (project in file("macros")).settings(
 libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.12.0"
)


lazy val core = (project in file("core")) dependsOn macros
