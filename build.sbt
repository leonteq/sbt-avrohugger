
(Global / run / fork) := true
(Global / run / connectInput) := true
(Global / run / outputStrategy) := Some(StdoutOutput)

lazy val avrohuggerVersion = "XXX"

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
      "com.julianpeeters" %% "avrohugger-core" % avrohuggerVersion,
      "com.julianpeeters" %% "avrohugger-filesorter" % avrohuggerVersion,
      "io.spray" %% "spray-json" % "1.3.6",
      "org.specs2" %% "specs2-core" % "4.20.2" % "test"),
    Test / publishArtifact := false,
    (Global / run / fork) := true,
    (Global / run / connectInput) := true,
    (Global / run / outputStrategy) := Some(StdoutOutput),
    artifactType := ArtifactType.JarLibrary,
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
