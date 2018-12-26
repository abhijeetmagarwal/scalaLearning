import org.apache.spark.rdd.RDD

import scala.util.Random
import scala.io.Source
import org.apache.spark.sql._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.Dataset


import org.apache.spark.sql.SparkSession
object ScalaPatternMatching extends  App{

  var x:Option[Int] = Some(0);

for(x <- 1 to 5) {

  x match {
    case 0 => println("Zero")
    case 1 => println("One")
    case 2 => println("Two")
    case other => println("Default")
  }
}
 /* val resourcesPath = getClass.getResource("\datanameAndAge.txt")
  println(resourcesPath.getPath)
*/
  /*val fileStream = getClass.getResourceAsStream("/nameAndAge.txt")
  val lines = Source.fromInputStream(fileStream).getLines
  lines.foreach(line => println(line))
*/
  val blockSize = if (args.length > 2) args(2) else "4096"

  val spark =  SparkSession.builder()
    .appName("Scala Pattern Matching")
    .config("spark.master","local")
    .config("spark.broadcast.blockSize",blockSize)
    .getOrCreate()



val input = spark.sparkContext.parallelize(Array("A","B","C","D"))

 input.map(x => x.equalsIgnoreCase("A")).foreach(println)
import  spark.implicits._

  println("Starting Dataframe")
 val inputDF =  input.toDF("col1")
  inputDF.select("col1").show()
inputDF.printSchema()

  println("Starting Dataset")
  case class inputDSCase (name: String)
  println("case Class defined")
  val inputDS  = inputDF.as[inputDSCase]

  inputDS.printSchema()
  println("Dataset defined")
  inputDS.show()



}
