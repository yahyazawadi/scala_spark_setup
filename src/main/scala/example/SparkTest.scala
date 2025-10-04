package example

import org.apache.spark.sql.SparkSession

object SparkTest {
  def main(args: Array[String]): Unit = {
    // Create Spark session
    val spark = SparkSession.builder()
      .appName("Spark IntelliJ Test")
      .master("local[*]") // Run locally using all cores
      .getOrCreate()

    // Create a simple DataFrame
    import spark.implicits._
    val data = Seq(
      ("Yahya", 21),
      ("Amoudi", 22),
      ("Scala", 23)
    ).toDF("name", "age")

    // Show data
    data.show()

    // Simple transformation
    val adults = data.filter($"age" >= 22)
    adults.show()

    spark.stop()
  }
}
