import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val `$name$` = project.in(file("."))
  .disablePlugins(MimaPlugin)
  .settings(commonSettings, releaseSettings, skipOnPublishSettings)
  .aggregate(core, docs)

lazy val core = project.in(file("core"))
  .settings(commonSettings, releaseSettings)
  .settings(
    name := "$name$"
  )

lazy val docs = project.in(file("docs"))
  .disablePlugins(MimaPlugin)
  .settings(commonSettings, skipOnPublishSettings, micrositeSettings)
  .dependsOn(core)
  .enablePlugins(MicrositesPlugin)
  .enablePlugins(TutPlugin)

lazy val contributors = Seq(
  "$contributorUsername$" -> "$contributorName$"
)

val catsV = "1.6.1"
val kittensV = "1.2.1"
val catsEffectV = "1.4.0"
val shapelessV = "2.3.3"
val fs2V = "2.0.0"
val http4sV = "0.20.11"
val circeV = "0.11.1"
val doobieV = "0.7.0"
val pureConfigV = "0.11.1"
val refinedV = "0.9.10"

val log4catsV = "0.3.0"
val catsParV = "0.2.1"
val catsTimeV = "0.2.0"
val fuuidV = "0.2.0"
val lineBackerV = "0.2.1"

val specs2V = "4.7.1"

val kindProjectorV = "0.10.3"
val betterMonadicForV = "0.3.1"

// General Settings
lazy val commonSettings = Seq(
  organization := "$organization$",

  scalaVersion := "$scala_version$",
  crossScalaVersions := Seq(scalaVersion.value, "$other_scala_version$"),

  scalacOptions in (Compile, doc) ++= Seq(
      "-groups",
      "-sourcepath", (baseDirectory in LocalRootProject).value.getAbsolutePath,
      "-doc-source-url", "https://github.com/$contributorUsername$/$name$/blob/v" + version.value + "€{FILE_PATH}.scala"
  ),

  addCompilerPlugin("org.typelevel" % "kind-projector" % kindProjectorV cross CrossVersion.binary),
  addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % betterMonadicForV),
  libraryDependencies ++= Seq(
    "org.typelevel"               %% "cats-core"                  % catsV,

    "org.typelevel"               %% "kittens"                    % kittensV,
    "org.typelevel"               %% "alleycats-core"             % catsV,

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

lazy val releaseSettings = {
  import ReleaseTransformations._
  Seq(
    releaseCrossBuild := true,
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      // For non cross-build projects, use releaseStepCommand("publishSigned")
      releaseStepCommandAndRemaining("+publishSigned"),
      setNextVersion,
      commitNextVersion,
      releaseStepCommand("sonatypeReleaseAll"),
      pushChanges
    ),
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    credentials ++= (
      for {
        username <- Option(System.getenv().get("SONATYPE_USERNAME"))
        password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
      } yield
        Credentials(
          "Sonatype Nexus Repository Manager",
          "oss.sonatype.org",
          username,
          password
        )
    ).toSeq,
    publishArtifact in Test := false,
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/$contributorUsername$/$name$"),
        "git@github.com:$contributorUsername$/$name$.git"
      )
    ),
    homepage := Some(url("https://github.com/$contributorUsername$/$name$")),
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    publishMavenStyle := true,
    pomIncludeRepository := { _ =>
      false
    },
    pomExtra := {
      <developers>
        {for ((username, name) <- contributors) yield
        <developer>
          <id>{username}</id>
          <name>{name}</name>
          <url>http://github.com/{username}</url>
        </developer>
        }
      </developers>
    }
  )
}

lazy val micrositeSettings = {
  import microsites._
  Seq(
    micrositeName := "$name$",
    micrositeDescription := "$project_description$",
    micrositeAuthor := "$contributorName$",
    micrositeGithubOwner := "$contributorUsername$",
    micrositeGithubRepo := "$name$",
    micrositeBaseUrl := "/$name$",
    micrositeDocumentationUrl := "https://www.javadoc.io/doc/$organization$/$name$_2.12",
    micrositeGitterChannelUrl := "$contributorUsername$/libraries", // Feel Free to Set To Something Else
    micrositeFooterText := None,
    micrositeHighlightTheme := "atom-one-light",
    micrositePalette := Map(
      "brand-primary" -> "#3e5b95",
      "brand-secondary" -> "#294066",
      "brand-tertiary" -> "#2d5799",
      "gray-dark" -> "#49494B",
      "gray" -> "#7B7B7E",
      "gray-light" -> "#E5E5E6",
      "gray-lighter" -> "#F4F3F4",
      "white-color" -> "#FFFFFF"
    ),
    fork in tut := true,
    scalacOptions in Tut --= Seq(
      "-Xfatal-warnings",
      "-Ywarn-unused-import",
      "-Ywarn-numeric-widen",
      "-Ywarn-dead-code",
      "-Ywarn-unused:imports",
      "-Xlint:-missing-interpolator,_"
    ),
    libraryDependencies += "com.47deg" %% "github4s" % "0.20.1",
    micrositePushSiteWith := GitHub4s,
    micrositeGithubToken := sys.env.get("GITHUB_TOKEN"),
    micrositeExtraMdFiles := Map(
        file("CHANGELOG.md")        -> ExtraMdFileConfig("changelog.md", "page", Map("title" -> "changelog", "section" -> "changelog", "position" -> "100")),
        file("CODE_OF_CONDUCT.md")  -> ExtraMdFileConfig("code-of-conduct.md",   "page", Map("title" -> "code of conduct",   "section" -> "code of conduct",   "position" -> "101")),
        file("LICENSE")             -> ExtraMdFileConfig("license.md",   "page", Map("title" -> "license",   "section" -> "license",   "position" -> "102"))
    )
  )
}

lazy val skipOnPublishSettings = Seq(
  skip in publish := true,
  publish := (()),
  publishLocal := (()),
  publishArtifact := false,
  publishTo := None
)
