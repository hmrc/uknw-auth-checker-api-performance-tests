import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"          %% "performance-test-runner"   % "6.0.0"         % Test,
    "io.github.wolfendale"    %% "scalacheck-gen-regexp"     % "1.1.0"      % Test,
    "org.scalacheck"          %% "scalacheck"                % "1.18.0"     % Test,
  )

}
