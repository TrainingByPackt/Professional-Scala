scalaVersion := "2.12.4"

lazy val doobieVersion = "0.5.0-M13"

libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core"   % doobieVersion,
  "org.tpolecat" %% "doobie-h2"     % doobieVersion
)
