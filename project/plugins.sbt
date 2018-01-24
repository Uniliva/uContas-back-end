addSbtPlugin("com.typesafe.sbt" % "sbt-twirl" % "1.3.12")
addSbtPlugin("org.scalatra.sbt" % "sbt-scalatra" % "1.0.1")
scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.2")
