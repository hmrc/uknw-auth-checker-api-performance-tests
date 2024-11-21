import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"          %% "performance-test-runner" % "6.1.0"  % Test,
    "io.github.wolfendale" %% "scalacheck-gen-regexp"   % "1.1.0"  % Test,
    "org.scalacheck"       %% "scalacheck"              % "1.18.1" % Test,
    "uk.gov.hmrc"          %% "bootstrap-test-play-30"  % "9.5.0"  % Test,
    "com.lihaoyi"          %% "upickle"                 % "4.0.2"  % Test
  )
}
