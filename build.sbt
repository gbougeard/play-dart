import com.typesafe.sbt.SbtNativePackager.packageArchetype
import com.typesafe.sbt.packager.Keys._
import com.typesafe.web.sbt.WebPlugin
import com.typesafe.jse.sbt.JsEnginePlugin
import com.typesafe.web.sbt.WebPlugin.WebKeys
import com.typesafe.jshint.sbt.JSHintPlugin
import Dependencies._


play.Project.playScalaSettings

name := "play-dart"

version := "1.0-SNAPSHOT"

packageArchetype.java_server

maintainer := "Gregory Bougeard <gbougeard@gmail.com>"

packageSummary := "play-dart"

packageDescription := "play-dart WIP"

scalaVersion := "2.10.2"

resolvers += Resolver.url("sbt-plugin-releases", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)


libraryDependencies ++= Seq(
  play.Project.jdbc,
  play.Project.cache,
  play.Project.filters,
  slf4j,
  logbackClassic,
  mysql,
  "joda-time" % "joda-time" % "2.3",
  "org.joda" % "joda-convert" % "1.5",
  jodaMapper,
  playSlick,
  playMetrics,
  secureSocial,
  scalaCheck
)     

play.Project.playScalaSettings

WebPlugin.webSettings

JsEnginePlugin.jsEngineSettings

JSHintPlugin.jshintSettings

//sourceDirectory in WebKeys.Assets := (sourceDirectory in Compile).value / "assets"

//WebKeys.jsFilter in WebKeys.Assets := new PatternFilter("""[^_].*\.js""".r.pattern)

//resourceDirectory in WebKeys.Assets := (baseDirectory in Compile).value / "public"

//resourceManaged in WebKeys.Assets := (classDirectory in Compile).value / "public"

dartEntryPoints ++= Seq("main.dart" , "opinion.dart")


//dartEntryPoints ++= Seq("main.dart", "opinion_ctrl.dart", "lib/domain.dart", "lib/component/opinion/opinion_component.dart")
//
//dartWebUIEntryPoints += "testwebui.html"

dartDev := true

dartVerbose := true
