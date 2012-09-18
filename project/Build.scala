import sbt._
import sbt.Keys._

object ProjectBuild extends Build {

  lazy val buildVersion =  "1.1-SNAPSHOT"
  lazy val systemProperties = new sys.SystemProperties()

  lazy val root = Project(id = "play-plugins-salat", base = file("."), settings = Project.defaultSettings).settings(
    organization := "se.radley",
    description := "MongoDB Salat plugin for PlayFramework 2",
    version := buildVersion,
//    scalaVersion := systemProperties.getOrElse("scala.version", "2.9.2"),
    resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
    resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
    libraryDependencies += "play" %% "play" % "2.1-SNAPSHOT",
    libraryDependencies += "play" %% "play-test" % "2.1-SNAPSHOT" % "test",
    libraryDependencies += "com.novus" %% "salat" % "1.9.1",

    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    pomExtra := (
      <url>http://github.com/leon/play-salat</url>
      <licenses>
        <license>
          <name>Apache 2.0</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:leon/play-salat.git</url>
        <connection>scm:git:git@github.com:leon/play-salat.git</connection>
      </scm>
      <developers>
        <developer>
          <id>leon</id>
          <name>Leon Radley</name>
          <url>http://leon.radley.se</url>
        </developer>
      </developers>
    ),
    publishTo <<= version { version: String =>
      val nexus = "https://oss.sonatype.org/"
      if (version.trim.endsWith("SNAPSHOT"))
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases" at nexus + "service/local/staging/deploy/maven2")
    }
  )
}
