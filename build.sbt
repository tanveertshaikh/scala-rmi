name := "scala-rmi"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "junit" % "junit" % "4.12"
)

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

addCompilerPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.3")
addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.3")