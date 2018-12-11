# My Giter 8 Template [![Build Status](https://travis-ci.org/ChristopherDavenport/library.g8.svg?branch=master)](https://travis-ci.org/ChristopherDavenport/library.g8)

My [Giter8](http://www.foundweekends.org/giter8/) template for new library projects.

```sbt
sbt new ChristopherDavenport/library.g8
```

## Example

[library-g8-example](https://github.com/ChristopherDavenport/library-g8-example)

## Setup

### Github Pages

1. Initial Gh-pages branch using the directions from [sbt-ghpages](https://github.com/sbt/sbt-ghpages/blob/7e2ee06180a5a955a89659915ec8cd75ea28147e/README.md#initializing-the-gh-pages-branch)

2. Go into Travis-CI.com, there you will need to configure and enter a value for environment variable `GITHUB_TOKEN` with a credential that can access your account. [Creating A Token](https://help.github.com/articles/creating-a-personal-access-token-for-the-command-line/)

### Sonatype Publishing

1. Setup Sonatype Account if you have not already. Good documentation on the process can be found on the [sbt-release-early wiki](https://github.com/scalacenter/sbt-release-early/wiki/How-to-release-with-Sonatype#you-dont-have-a-sonatype-account)

2. Go into Travis-CI.com, there you will need to configure and enter values for environment variables `SONATYPE_USERNAME` and `SONATYPE_PASSWORD` for publishing SNAPSHOTS to sonatype.