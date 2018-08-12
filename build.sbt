import Dependencies._

lazy val It = config("it") extend Test

lazy val root =
  (project in file("."))
    .enablePlugins(PlayScala, BuildInfoPlugin)
    .configs(It)
    .settings(
      name := "database-performance-testing",
      organization := "com.ruchij",
      version := "0.0.1",
      scalaVersion := "2.12.6",
      libraryDependencies ++= List(guice, jodaTime, playSlick, playSlickEvolutions, postgresql),
      libraryDependencies ++= List(scalaTestPlusPlay, h2, javaFaker).map(_ % Test),
      buildInfoKeys := BuildInfoKey.ofN(name, organization, version, sbtVersion, scalaVersion),
      buildInfoPackage := "com.eed3si9n",
      javaOptions in Test += "-Dconfig.file=conf/application.test.conf",
      inConfig(It)(Defaults.itSettings),
      scalaSource in It := baseDirectory.value / "it"
    )