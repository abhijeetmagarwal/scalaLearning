import org.apache.spark.sql.functions._
import org.apache.spark.sql._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{DataFrame, Dataset}

import scala.collection.mutable
import scala.collection.immutable.ListMap

object implicitExamples extends  App{

  def printNames(implicit  s : String) = println("Yo " +s)

  printNames("Abhijeet")

  implicit val  namesTobePassed = "Rohit"

  printNames

/*
  val spark = SparkSession.builder()
        .appName("Normal Operation on Rdd")
        .config("spark.master","local")
    .getOrCreate()
*/


 // import  spark.implicits._
/*
val input = spark.sparkContext.textFile("file:///D://Big Data//Data")
input.take(10)
*/

  val input:Array[Array[String]] = Array(Array("1001","Sam,Chennai","Tamil Nadu","600001"),
    Array("1002","John,Bangalore","Karnataka","560044"))


//val input1 =   spark.sparkContext.parallelize(input,2)
  /*Array(1003,Bill,Hyderabad,Andhra Pradesh,500001),
    Array(1004,Jennifer,Bangalore,Karnataka,560044),
    Array(1005,Charles,Bangalore,Karnataka,560044),
    Array(1006,Mary,Chennai,Tamil Nadu,600001),
    Array(1007,Stella,Hyderabad,Andhra Pradesh,500001))
*/
//input1.take(1)

val v = Vector(1,2,3)

println ("Vector Sum" + v.sum)
println(v.filter(_ > 1))
println(v.map(_*2))

//val StudentNames[String] = List("A","B","C","D")

  val em: List[Nothing] = List()
  val a = List("Abhijeet","India",10,"1990-09-11")
  val b = List("Nitesh","India",20,"1990-09-12")
  val c = a ::: b
  println(c)
  val number = Seq(1,2,3)
  println(number.max)

val listmapvar =  ListMap(1 ->  "Abhijeet")

println(listmapvar)

println(  listmapvar.get(1))

val ch = ListMap(2 -> "Farhan")

val union = listmapvar ++ ch
  println(union )

val map = Map(1-> "Abhijeet", 2-> "Chirag")
  println(map)
  println(map.get(1))
if (map.get(1) == Some("Abhijeet"))
{
  println("Abhijeet is present in map")
}
  val map2:Map[String,String] = Map(("PD","Plain Donut"),("SD","Strawberry Donut"),("CD","Chocolate Donut"))
  println(map2)

  val mapminus = map2 - ("PD")

  println(s"mapMinus : $mapminus")
val mapempty = Map.empty[String,String]

  println(s"empty string"+mapempty)

  println(Math.sqrt(2))


def doubler(i:Int) : Int =
{
  return i * 2
}

val callFunction = doubler(2)

val Anonymous = (i:Int) => {i * 2}

println(Anonymous(5))



  val customers:Array[String] = Array("Abhijeet","Rahul","Navaneet","Nitesh")


  for (i <- 0 until customers.size)
{
  println(s"""Iteration :-> $i  Hi ${customers(i)}""")
}

  /////Anonymous Function ////////////////

def paymentReminder(s:String) = println(s"""Payment Reminder for  $s """)


  for (i <- 0 until customers.size)
  {
    paymentReminder(customers(i))
  }


  def forEach(a:Array[String], f:String => Unit) = {
    for (i <- 0 until a.size) {
      f(a(i))
    }
  }

  println(forEach(customers,paymentReminder))

customers.foreach(i => paymentReminder(i))

/////Anonymous Function ////////////////







}





