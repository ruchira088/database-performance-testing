import Dependencies._

lazy val root =
  (project in file("."))
    .enablePlugins(PlayScala)
    .settings(
      name := "database-performance-testing",
      organization := "com.ruchij",
      version := "0.0.1",
      scalaVersion := "2.12.6",
      libraryDependencies ++= List(guice, jodaTime),
      libraryDependencies ++= List(scalaTestPlusPlay).map(_ % Test)
    )