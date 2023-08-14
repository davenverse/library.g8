ThisBuild / tlBaseVersion := "0.0" // your current series x.y

ThisBuild / organization := "io.chrisdavenport"
ThisBuild / organizationName := "Christopher Davenport"
ThisBuild / licenses := Seq(License.MIT)
ThisBuild / developers := List(
  tlGitHubDev("christopherdavenport", "Christopher Davenport")
)
ThisBuild / versionScheme := Some("early-semver")

ThisBuild / tlCiReleaseBranches := Seq("main")
ThisBuild / tlSonatypeUseLegacyHost := true


val Scala3 = "$scala_version$"

ThisBuild / crossScalaVersions := Seq("$other_scala_version$", Scala3)
ThisBuild / scalaVersion := Scala3

ThisBuild / testFrameworks += new TestFramework("munit.Framework")

val catsV = "2.10.0"
val catsEffectV = "3.4.8"
val fs2V = "3.6.1"
val http4sV = "0.23.18"
val circeV = "0.14.5"
val doobieV = "1.0.0-RC2"
val munitCatsEffectV = "1.0.7"


// Projects
lazy val `$name$` = tlCrossRootProject
  .aggregate(core)

lazy val core = crossProject(JVMPlatform, JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .settings(
    name := "$name$",

    libraryDependencies ++= Seq(
      "org.typelevel"               %%% "cats-core"                  % catsV,
      "org.typelevel"               %%% "cats-effect"                % catsEffectV,

      "co.fs2"                      %%% "fs2-core"                   % fs2V,
      "co.fs2"                      %%% "fs2-io"                     % fs2V,

      "org.http4s"                  %%% "http4s-dsl"                 % http4sV,
      "org.http4s"                  %%% "http4s-ember-server"        % http4sV,
      "org.http4s"                  %%% "http4s-ember-client"        % http4sV,
      "org.http4s"                  %%% "http4s-circe"               % http4sV,

      "io.circe"                    %%% "circe-core"                 % circeV,
      "io.circe"                    %%% "circe-generic"              % circeV,
      "io.circe"                    %%% "circe-parser"               % circeV,

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
  .enablePlugins(TypelevelSitePlugin)
  .dependsOn(core.jvm)
