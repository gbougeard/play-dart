import sbt._
import Keys._

object Dependencies {
  // Versions
  val akkaVersion = "2.2.2"
  val logbackVersion = "1.1.1"
  val slf4jVersion = "1.7.6"
  val mysqlVersion = "5.1.28"
  val jodaMapperVersion = "1.0.1"
  val playSlickVersion = "0.6.0.1"
  //  val metricsVersion                  = "3.0.0"
  val secureSocialVersion = "2.1.2"
  val playMetricsVersion = "0.1.3"

  val scalaCheckVersion = "1.11.0"
  val scalazVersion = "7.0.4"

//  val webjarsVersion = "2.2.1"
//  val wjAngularVersion = "1.2.13"
//  val wjAngularUiVersion = "0.4.0-1"
//  val wjAngularUiBootstrapVersion = "0.10.0"
//  val wjAngularStrapVersion = "2.0.1-rc.3-1"
//  val wjBootstrapVersion = "3.1.1"
//  val wjBootswatchVersion = "3.1.1"
//  val wjBsDatepickerVersion = "1.3.0"
//  val wjBsTimepickerVersion = "0.2.3"
//  val wjPNotifyVersion = "1.2.0"
//  val wjCodeMirrorVersion = "3.21"
//  val wjFontAwesomeVersion = "4.0.3"
//  val wjMomentjsVersion = "2.5.0"
//  val wjSelect2Version = "3.4.4"
//  val wjJqueryVersion = "1.11.0"
//  val wjJqueryUiVersion = "1.10.3"

  // Libraries
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  val logbackClassic = "ch.qos.logback" % "logback-classic" % logbackVersion
  val slf4j = "org.slf4j" % "slf4j-api" % slf4jVersion
  val mysql = "mysql" % "mysql-connector-java" % mysqlVersion
  val jodaMapper = "com.github.tototoshi" %% "slick-joda-mapper" % jodaMapperVersion
  val playSlick = "com.typesafe.play" %% "play-slick" % playSlickVersion
  //  val metrics               = "nl.grons" % "metrics-scala_2.10" % metricsVersion
  val playMetrics = "com.kenshoo" %% "metrics-play" % playMetricsVersion
  val secureSocial = "securesocial" %% "securesocial" % secureSocialVersion
  val scalaCheck = "org.scalacheck" %% "scalacheck" % scalaCheckVersion
  val scalaz = "org.scalaz" %% "scalaz-core" % scalazVersion

//  val webjars = "org.webjars" % "webjars-play_2.10" % webjarsVersion
//  val wjAngular = "org.webjars" % "angularjs" % wjAngularVersion
//  val wjAngularUi = "org.webjars" % "angular-ui" % wjAngularUiVersion
//  val wjAngularUiBootstrap = "org.webjars" % "angular-ui-bootstrap" % wjAngularUiBootstrapVersion
//  val wjAngularStrap = "org.webjars" % "angular-strap" % wjAngularStrapVersion
//  val wjBootstrap = "org.webjars" % "bootstrap" % wjBootstrapVersion
//  val wjPNtofify = "org.webjars" % "pnotify" % wjPNotifyVersion
//  val wjBootstrapDatepicker = "org.webjars" % "bootstrap-datepicker" % wjBsDatepickerVersion
//  val wjBootstrapTimepicker =  "org.webjars" % "bootstrap-timepicker" % wjBsTimepickerVersion
//  val wjBootswatch=  "org.webjars" % "bootswatch" % wjBootswatchVersion
//  val wjFamfamFlag =  "org.webjars" % "famfamfam-flags" % "0.0"
//  val wjFontAwesome =  "org.webjars" % "font-awesome" % wjFontAwesomeVersion
//  val wjJquery =  "org.webjars" % "jquery" % wjJqueryVersion
//  val wjJqueryUi = "org.webjars" % "jquery-ui" % wjJqueryUiVersion
//  val wjMomentjs = "org.webjars" % "momentjs" % wjMomentjsVersion
//  val wjSelect2 = "org.webjars" % "select2" % wjSelect2Version
//  val wjCodeMirror = "org.webjars" % "codemirror" % wjCodeMirrorVersion
//  val wjPrettify = "org.webjars" % "prettify" % "4-Mar-2013"
}