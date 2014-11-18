name := """ssrs"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.apache.mahout" % "mahout-core" % "0.9",
  "mysql" % "mysql-connector-java" % "5.1.32"
)
