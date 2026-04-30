import com.peknight.build.gav
import com.peknight.build.gav.*
import com.peknight.build.sbt.*

commonSettings

lazy val scalaCheck = (project in file("."))
  .settings(name := "scalacheck")
  .aggregate(
    scalaCheckCore.jvm,
    scalaCheckCore.js,
    scalaCheckCore.native,
  )

lazy val scalaCheckCore = (crossProject(JVMPlatform, JSPlatform, NativePlatform) in file("scalacheck-core"))
  .settings(name := "scalacheck-core")
  .settings(crossDependencies(gav.scalaCheck))
  .nativeSettings(crossDependencyOverrides(gav.scalaNative.testInterface))
