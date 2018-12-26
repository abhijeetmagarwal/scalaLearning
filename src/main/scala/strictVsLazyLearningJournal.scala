import scala.collection.mutable.ArrayBuffer

object strictVsLazyLearningJournal extends  App{

  def Factorial(i:Int):Int =
  {
     if (i<=0) {
       return 1
     }
      else {
    i * Factorial(i - 1)
    }
  }
  println(Factorial(5))
  //Abother way of calling recursion in scala
  def FactorialNew(i:Int):Int = {
  println("Calling Factorial function for:-> " + i)

    def facttemp(n: Int, f: Int): Int = {
      if (n <= 0) f
      else facttemp(n - 1, n * f)
    }

    return facttemp(i, 1)
  }

  println(FactorialNew(3))


  println("-----Twice Function Started-------")
def twice(i:Int) ={

  i + i

}
println(twice(FactorialNew(15)/FactorialNew(11)))

//Highher order function if executed in strict mode might cause memory issue

  def twiceH (f: => Int ) =
  {
   val i =  f
    i + i

  }
println("twiceH"+twiceH({FactorialNew(15)/FactorialNew(11)}))
  println(twiceH({FactorialNew(15)/FactorialNew(11)}))


  case class Message (id:String,message:String)

  val messageQueue = List(
    Message("rahul@gmail.com","Hi"),
    Message("navaneet@gmail.com","Hi"),
    Message("abhi@gmail.com","Hello1"),
    Message("abhi@yahoo.com","Hello"),
    Message("vishal@gmail.com","Hi")
    )

  println(messageQueue)

  /*  for (i <- 0 until messageQueue.size)
  {
    val mes1 = messageQueue(i).id.split("@").toList
    val mes2 = messageQueue(i+1).id.split("@").toList

    if ( mes1(0) ==  mes2(0) )
    println("match found")
  }
*/

//Closure Example from Learning Journal
  def getComputation  = (empId:Int) => {

    val e:Map[Int,Double] = Map((1,100.0),(2,200.0),(3,300.0),(4,400.0))
    val percentHike:Map[Int,Double] = Map((1,10.0),(2,20.0),(3,30.0),(4,40.0))

    (empId,e(empId) * percentHike(empId)/100)
  }

val getHike = getComputation

println(getHike(1))

println(for (i <- 1 to 10 if (i % 2) == 0 ) yield (i))
  val s = for (i <- 1 to 10 if (i % 2) == 0 ) yield (i)
println(s.max)
val a = Array(1,2,3,4,5)
val Array1 = new Array[String](10)
  println(Array1.size)

  for (a <- Array1)
    println(a)

val buffer = ArrayBuffer[Int](1)

  println(buffer.insert(1))

  val mkstring = "Abhijeet Agarwal"
  println(mkstring.mkString(" "))

}
