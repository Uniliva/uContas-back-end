organization := "com.unitec"
name := "uContas"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.12.3"

val ScalatraVersion = "2.6.+"


resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra"            %% "scalatra"          % ScalatraVersion,
  "org.scalatra"            %% "scalatra-scalate"  % ScalatraVersion,
  "org.scalatra"            %% "scalatra-specs2"   % ScalatraVersion    % "test",
  "com.typesafe.slick"      %% "slick"             % "3.2.0",
   "mysql" % "mysql-connector-java" % "5.1.42",
  "com.mchange"             %  "c3p0"              % "0.9.5.2",
  "org.eclipse.jetty"       %  "jetty-webapp"      % "9.4.6.v20170531"  % "provided",
  "javax.servlet"           %  "javax.servlet-api" % "3.1.0"            % "provided",
  "junit"                   % "junit"              % "4.11" % Test,
 "org.specs2"               %% "specs2-core"       % "3.6.1"            % "test"
)
// https://mvnrepository.com/artifact/org.specs2/specs2-junit
libraryDependencies += "org.specs2" %% "specs2-junit" % "4.0.1" % Test




enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)

enablePlugins(JettyPlugin)
containerPort in Jetty := 8070