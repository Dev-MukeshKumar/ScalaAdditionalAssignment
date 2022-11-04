ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.3"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaAdditionalAssignment"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"