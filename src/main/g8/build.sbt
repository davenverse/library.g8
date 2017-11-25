
lazy val $name$ = project.in(file("."))
    .settings(commonSettings)
    .settings(
      name := "$name$"
    )

/***********************************************************************\
                      Boilerplate below these lines
\***********************************************************************/




lazy val commonSettings = Seq(
  organization := "$organization$",
  licenses += ("MIT", url("https://opensource.org/licenses/MIT")),

  addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.4" cross CrossVersion.binary),

  libraryDependencies ++= Seq(
//    "org.typelevel"               %% "cats-core"                  % "1.0.0-RC1",
//
//    "org.typelevel"               %% "kittens"                    % "1.0.0-RC1",
//    "org.typelevel"               %% "alleycats-core"             % "1.0.0-RC1",
//    "org.typelevel"               %% "mouse"                      % "0.12",
//
//    "org.typelevel"               %% "cats-effect"                % "0.5",
//
//    "com.chuusai"                 %% "shapeless"                  % "2.3.2",
//
//    "co.fs2"                      %% "fs2-core"                   % "0.10.0-M8",
//    "co.fs2"                      %% "fs2-io"                     % "0.10.0-M8",
//
//    "org.http4s"                  %% "http4s-dsl"                 % "0.18.0-M3",
//    "org.http4s"                  %% "http4s-blaze-server"        % "0.18.0-M3",
//    "org.http4s"                  %% "http4s-blaze-client"        % "0.18.0-M3",
//    "org.http4s"                  %% "http4s-circe"               % "0.18.0-M3",
//
//    "io.circe"                    %% "circe-core"                 % "0.9.0-M2",
//    "io.circe"                    %% "circe-generic"              % "0.9.0-M2",
//    "io.circe"                    %% "circe-parser"               % "0.9.0-M2",
//
//    "io.circe"                    %% "circe-fs2"                  % "0.9.0-M3",
//
//    "org.tpolecat"                %% "doobie-core"                % "0.5.0-M9",
//    "org.tpolecat"                %% "doobie-h2"                  % "0.5.0-M9",
//    "org.tpolecat"                %% "doobie-hikari"              % "0.5.0-M9",
//    "org.tpolecat"                %% "doobie-postgres"            % "0.5.0-M9",
//    "org.tpolecat"                %% "doobie-specs2"              % "0.5.0-M9",
//
//    "com.github.pureconfig"       %% "pureconfig"                 % "0.8.0",
//
//    "eu.timepit"                  %% "refined"                    % "0.8.4",
//    "eu.timepit"                  %% "refined-scalacheck"         % "0.8.4"       % Test,

    "org.specs2"                  %% "specs2-core"                % "4.0.1"       % Test,
    "org.specs2"                  %% "specs2-scalacheck"          % "4.0.1"       % Test,
    "org.typelevel"               %% "discipline"                 % "0.8"         % Test,
    "com.github.alexarchambault"  %% "scalacheck-shapeless_1.13"  % "1.1.6"       % Test
  )
)
