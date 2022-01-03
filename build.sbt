import com.typesafe.config.ConfigFactory

name := """cinema-api"""
organization := "com.rafael"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
lazy val slick = taskKey[Seq[File]]("Generate Tables.scala")

val conf = ConfigFactory.parseFile(new File("conf/application.conf")).resolve()

scalaVersion := "2.13.7"

libraryDependencies ++= Seq(
  guice,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "com.typesafe.play" %% "play-slick" % "4.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "4.0.2",
  "com.typesafe.slick" %% "slick-codegen" % "3.3.3",
  "mysql" % "mysql-connector-java" % "8.0.16",
  "net.codingwell" %% "scala-guice" % "4.2.5"
)

slick := {
  val classPath = (dependencyClasspath in Compile).value
  val r = (runner in Compile).value
  val s = streams.value

  val outputDir = "app"

  val profile = conf.getString("slick.dbs.default.profile").dropRight(1)
  val url = conf.getString("slick.dbs.default.db.url")
  val jdbcDriver = conf.getString("slick.dbs.default.db.driver")
  val user = conf.getString("slick.dbs.default.db.user")
  val password = conf.getString("slick.dbs.default.db.password")

  val pkg = "models"

  r.run("slick.codegen.SourceCodeGenerator", classPath.files, Array(profile, jdbcDriver, url, outputDir, pkg, user, password), s.log).failed foreach (sys error _.getMessage)
  val fname = outputDir + s"/$pkg/Tables.scala"
  Seq(file(fname))
}
