addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8" % "0.13.1")
libraryDependencies += { "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value }

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.1")
