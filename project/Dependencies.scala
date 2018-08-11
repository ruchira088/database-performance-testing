import sbt._

object Dependencies
{
  lazy val jodaTime = "joda-time" % "joda-time" % "2.10"

  lazy val playSlick = "com.typesafe.play" %% "play-slick" % "3.0.3"

  lazy val playSlickEvolutions = "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3"

  lazy val postgresql = "org.postgresql" % "postgresql" % "42.2.4"

  lazy val scalaTestPlusPlay = "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2"
}
