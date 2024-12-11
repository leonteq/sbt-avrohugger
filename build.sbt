//ThisBuild / organization := "com.julianpeeters"
//ThisBuild / description := "Sbt plugin for compiling Avro to Scala"
//ThisBuild / version := "2.8.4"
//ThisBuild / versionScheme := Some("semver-spec")

//enablePlugins(SbtPlugin)
//enablePlugins(EPortalSbtSettings)

(Global / run / fork) := true
(Global / run / connectInput) := true
(Global / run / outputStrategy) := Some(StdoutOutput)

//ThisBuild / scalaVersion := "2.12.20"
//ThisBuild / crossSbtVersions := Seq(sbtVersion.value)
//ThisBuild / scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ywarn-value-discard")

//ThisBuild / libraryDependencies ++= Seq(
//  "com.julianpeeters" %% "avrohugger-core" % "3.0.6",
//  "com.julianpeeters" %% "avrohugger-filesorter" % "3.0.6",
//  "io.spray" %% "spray-json" % "1.3.6",
//  "org.specs2" %% "specs2-core" % "4.20.2" % "test")

//ThisBuild / publishMavenStyle := true
//Test / publishArtifact := false
//ThisBuild / publishTo := {
//  val nexus = "https://oss.sonatype.org/"
//  if (version.value.trim.endsWith("SNAPSHOT"))
//    Some("snapshots" at nexus + "content/repositories/snapshots")
//  else
//    Some("releases" at nexus + "service/local/staging/deploy/maven2")
//}

//ThisBuild / artifactType := ArtifactType.JarLibrary
////ThisBuild / releaseIgnoreUntrackedFiles := true
//ThisBuild / Release.parentReleaseSettings(Seq.empty, "sbt-avrohugger", buildRootProject = true)
//ThisBuild / pomIncludeRepository := { _ => false }
//ThisBuild / licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
//ThisBuild / homepage := Some(url(s"https://github.com/julianpeeters/${name.value}"))
//ThisBuild / pomExtra := (
//  <scm>
//    <url>git://github.com/julianpeeters/sbt-avrohugger.git</url>
//    <connection>scm:git://github.com/julianpeeters/sbt-avrohugger.git</connection>
//  </scm>
//  <developers>
//    <developer>
//      <id>julianpeeters</id>
//      <name>Julian Peeters</name>
//      <url>http://github.com/julianpeeters</url>
//    </developer>
//  </developers>)
//
//ThisBuild / scriptedLaunchOpts := { scriptedLaunchOpts.value ++
//  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
//}
//ThisBuild / scriptedBufferLog := false

lazy val sbtavrohugger = (project in file("."))
  .enablePlugins(SbtPlugin, EPortalSbtSettings)
  .settings(
    organization := "com.julianpeeters",
    description := "Sbt plugin for compiling Avro to Scala",
    versionScheme := Some("semver-spec"),
    scalaVersion := "2.12.20",
    crossSbtVersions := Seq(sbtVersion.value),
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Ywarn-value-discard"),
    libraryDependencies ++= Seq(
      "com.julianpeeters" %% "avrohugger-core" % "3.0.9",
      "com.julianpeeters" %% "avrohugger-filesorter" % "3.0.9",
      "io.spray" %% "spray-json" % "1.3.6",
      "org.specs2" %% "specs2-core" % "4.20.2" % "test"),
    artifactType := ArtifactType.JarLibrary,
    Test / publishArtifact := false,
    (Global / run / fork) := true,
    (Global / run / connectInput) := true,
    (Global / run / outputStrategy) := Some(StdoutOutput),
      //ThisBuild / releaseIgnoreUntrackedFiles := true
    Release.parentReleaseSettings(Seq.empty, "sbtavrohugger", buildRootProject = true),
    pomIncludeRepository := { _ => false },
    licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url(s"https://github.com/julianpeeters/${name.value}")),
    pomExtra := (
      <scm>
        <url>git://github.com/julianpeeters/sbt-avrohugger.git</url>
        <connection>scm:git://github.com/julianpeeters/sbt-avrohugger.git</connection>
      </scm>
        <developers>
          <developer>
            <id>julianpeeters</id>
            <name>Julian Peeters</name>
            <url>http://github.com/julianpeeters</url>
          </developer>
        </developers>),
      scriptedLaunchOpts := { scriptedLaunchOpts.value ++
        Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
      },
    scriptedBufferLog := false,
  )
