import com.peknight.build.gav
import com.peknight.build.gav.{scalaCheck as _, *}
import com.peknight.build.gav.scala.scala3
import com.peknight.build.sbt.*

commonSettings

lazy val scalaCheck = (project in file("."))
  .settings(name := "scalacheck")
  .aggregate(scalaCheckCore.projectRefs *)

lazy val scalaCheckCore = (projectMatrix in file("scalacheck-core"))
  .settings(name := "scalacheck-core")
  .settings(libraryDependencies ++= dependencies(gav.scalaCheck))
  .jvmPlatform(scalaVersions = Seq(scala3.version))
  .jsPlatform(scalaVersions = Seq(scala3.version))
