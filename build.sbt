val catsV = "1.6.1"
val kittensV = "1.2.1"
val catsEffectV = "1.4.0"
val mouseV = "0.20"
val shapelessV = "2.3.3"
val fs2V = "2.0.0"
val http4sV = "0.20.10"
val circeV = "0.11.1"
val doobieV = "0.7.0"
val pureConfigV = "0.11.1"
val refinedV = "0.9.9"

val log4catsV = "0.3.0"
val catsParV = "0.2.1"
val catsTimeV = "0.2.0"
val fuuidV = "0.2.0"
val lineBackerV = "0.2.1"

val specs2V = "4.7.0"

val kindProjectorV = "0.10.3"
val betterMonadicForV = "0.3.1"

// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(
    name := "base",
    Keys.test in Test := {
      val _ = (g8Test in Test).toTask("").value
    },
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns),

    addCompilerPlugin("org.typelevel" % "kind-projector" % kindProjectorV cross CrossVersion.binary),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % betterMonadicForV),
    libraryDependencies ++= Seq(
      "org.typelevel"               %% "cats-core"                  % catsV,
  
      "org.typelevel"               %% "kittens"                    % kittensV,
      "org.typelevel"               %% "alleycats-core"             % catsV,
      "org.typelevel"               %% "mouse"                      % mouseV,
  
      "org.typelevel"               %% "cats-effect"                % catsEffectV,
  
      "com.chuusai"                 %% "shapeless"                  % shapelessV,
  
      "co.fs2"                      %% "fs2-core"                   % fs2V,
      "co.fs2"                      %% "fs2-io"                     % fs2V,
  
      "org.http4s"                  %% "http4s-dsl"                 % http4sV,
      "org.http4s"                  %% "http4s-blaze-server"        % http4sV,
      "org.http4s"                  %% "http4s-blaze-client"        % http4sV,
      "org.http4s"                  %% "http4s-circe"               % http4sV,
  
      "io.circe"                    %% "circe-core"                 % circeV,
      "io.circe"                    %% "circe-generic"              % circeV,
      "io.circe"                    %% "circe-parser"               % circeV,
  
      "org.tpolecat"                %% "doobie-core"                % doobieV,
      "org.tpolecat"                %% "doobie-h2"                  % doobieV,
      "org.tpolecat"                %% "doobie-hikari"              % doobieV,
      "org.tpolecat"                %% "doobie-postgres"            % doobieV,
      "org.tpolecat"                %% "doobie-specs2"              % doobieV       % Test,
  
      "io.chrisdavenport"           %% "log4cats-core"              % log4catsV,
      "io.chrisdavenport"           %% "log4cats-slf4j"             % log4catsV,
      "io.chrisdavenport"           %% "log4cats-extras"            % log4catsV,
      "io.chrisdavenport"           %% "log4cats-testing"           % log4catsV     % Test,
  
      "io.chrisdavenport"           %% "cats-par"                   % catsParV,
      "io.chrisdavenport"           %% "cats-time"                  % catsTimeV,
  
      "io.chrisdavenport"           %% "linebacker"                 % lineBackerV,
  
      "io.chrisdavenport"           %% "fuuid"                      % fuuidV,
  
      "com.github.pureconfig"       %% "pureconfig"                 % pureConfigV,
  
      "eu.timepit"                  %% "refined"                    % refinedV,
      "eu.timepit"                  %% "refined-scalacheck"         % refinedV      % Test,
  
      "org.specs2"                  %% "specs2-core"                % specs2V       % Test,
      "org.specs2"                  %% "specs2-scalacheck"          % specs2V       % Test
    )
)

