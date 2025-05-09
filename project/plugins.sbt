resolvers += "HMRC-open-artefacts-maven" at "https://open.artefacts.tax.service.gov.uk/maven2"
resolvers += Resolver.url("HMRC-open-artefacts-ivy", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(
  Resolver.ivyStylePatterns
)

addSbtPlugin("uk.gov.hmrc"     % "sbt-auto-build"        % "3.24.0")
addSbtPlugin("io.gatling"      % "gatling-sbt"           % "4.13.2")
addSbtPlugin("org.scalameta"   % "sbt-scalafmt"          % "2.5.4")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0" exclude ("org.scala-lang.modules", "scala-xml_2.12"))
addSbtPlugin("ch.epfl.scala"   % "sbt-scalafix"          % "0.14.2")
