name := "spark-example"
version := "0.1"
scalaVersion := "2.12.18"    // or 2.12.20 if you prefer and it resolves

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.apache.spark" %% "spark-core" % "3.5.7",
  "org.apache.spark" %% "spark-sql"  % "3.5.7"
)

// Optional assembly settings (only needed for spark-submit builds)
assembly / assemblyJarName := "spark-example-assembly.jar"
assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => sbtassembly.MergeStrategy.discard
  case _ => sbtassembly.MergeStrategy.first
}
