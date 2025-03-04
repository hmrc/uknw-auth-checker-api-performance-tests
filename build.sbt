import sbt.Compile
import sbt.Keys.baseDirectory

ThisBuild / scalaVersion := "2.13.16"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

lazy val root = (project in file("."))
  .enablePlugins(GatlingPlugin)
  .settings(
    name := "uknw-auth-checker-api-performance-tests",
    version := "0.1.0-SNAPSHOT",
    // implicitConversions & postfixOps are Gatling recommended -language settings
    scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-language:postfixOps"),
    // Enabling sbt-auto-build plugin provides DefaultBuildSettings with default `testOptions` from `sbt-settings` plugin.
    // These testOptions are not compatible with `sbt gatling:test`. So we have to override testOptions here.
    Test / testOptions := Seq.empty,
    libraryDependencies ++= Dependencies.test
  )

addCommandAlias("fmtAll", ";scalafmtSbt;scalafmtAll")
addCommandAlias("preCommit", ";clean;compile;fmtAll;scalafixAll;scalastyle")
