---
layout: home

---

# $name$ - $project_description$ [![Build Status](https://travis-ci.com/$contributorUsername$/$name$.svg?branch=master)](https://travis-ci.com/$contributorUsername$/$name$) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/$organization$/$name$_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/$organization$/$name$_2.12)

## Quick Start

To use $name$ in an existing SBT project with Scala 2.11 or a later version, add the following dependencies to your
`build.sbt` depending on your needs:

```scala
libraryDependencies ++= Seq(
  "$organization$" %% "$name$" % "<version>"
)
```