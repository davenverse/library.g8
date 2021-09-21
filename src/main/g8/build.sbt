import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

val Scala213 = "$scala_version$"

ThisBuild / crossScalaVersions := Seq("$other_scala_version$", Scala213)
ThisBuild / scalaVersion := Scala213

ThisBuild / testFrameworks += new TestFramework("munit.Framework")

val catsV = "2.6.1"
val catsEffectV = "3.2.9"
val fs2V = "3.1.2"
val http4sV = "0.23.4"
val circeV = "0.14.1"
val doobieV = "1.0.0-RC1"
val munitCatsEffectV = "1.0.5"


// Projects
lazy val `$name$` = project.in(file("."))
  .disablePlugins(MimaPlugin)
  .enablePlugins(NoPublishPlugin)
  .aggregate(core.jvm, core.js)

lazy val core = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .settings(
    name := "$name$",

    libraryDependencies ++= Seq(
      "org.typelevel"               %% "cats-core"                  % catsV,
      "org.typelevel"               %% "cats-effect"                % catsEffectV,

      "co.fs2"                      %% "fs2-core"                   % fs2V,
      "co.fs2"                      %% "fs2-io"                     % fs2V,

      "org.http4s"                  %% "http4s-dsl"                 % http4sV,
      "org.http4s"                  %% "http4s-ember-server"        % http4sV,
      "org.http4s"                  %% "http4s-ember-client"        % http4sV,
      "org.http4s"                  %% "http4s-circe"               % http4sV,

      "io.circe"                    %% "circe-core"                 % circeV,
      "io.circe"                    %% "circe-generic"              % circeV,
      "io.circe"                    %% "circe-parser"               % circeV,

      "org.tpolecat"                %% "doobie-core"                % doobieV,
      "org.tpolecat"                %% "doobie-h2"                  % doobieV,
      "org.tpolecat"                %% "doobie-hikari"              % doobieV,
      "org.tpolecat"                %% "doobie-postgres"            % doobieV,

      "org.typelevel"               %%% "munit-cats-effect-3"        % munitCatsEffectV         % Test,

    )
  ).jsSettings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule)},
  )

lazy val site = project.in(file("site"))
  .disablePlugins(MimaPlugin)
  .enablePlugins(DavenverseMicrositePlugin)
  .dependsOn(core.jvm)
  .settings{
    import microsites._
    Seq(
      micrositeDescription := "$project_description$",
    )
  }
