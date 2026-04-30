import com.peknight.build.gav
import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val scalaCheck = (project in file("."))
  .settings(name := "scalacheck")
  .aggregate(
    scalaCheckCore.jvm,
    scalaCheckCore.js,
  )

lazy val scalaCheckCore = (crossProject(JVMPlatform, JSPlatform) in file("scalacheck-core"))
  .settings(name := "scalacheck-core")
  .settings(crossDependencies(gav.scalaCheck))
