package example

import org.apache.spark.sql.SparkSession

object WordCountRDD {
  def main(args: Array[String]): Unit = {
    if (args.length < 1) {
      System.err.println("Usage: WordCountRDD <input>")
      System.exit(1)
    }
    val input = args(0)

    val spark = SparkSession.builder()
      .appName("WordCountRDD")
      .master("local[*]")
      .getOrCreate()

    val sc = spark.sparkContext

    val lines = sc.textFile(input)
    val counts = lines
      .flatMap(_.split("\\W+"))
      .filter(_.nonEmpty)
      .map(_.toLowerCase)
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .collect()
      .sortBy(-_._2)

    println("Word counts:")
    counts.foreach { case (w, c) => println(s"$w -> $c") }

    spark.stop()
  }
}
