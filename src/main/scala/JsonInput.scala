object JsonInput {

  import org.apache
  import org.apache.spark.sql.SparkSession
  import org.apache.hadoop.fs.FileSystem
  import org.apache.hadoop.fs.Path

  def main(args: Array[String]): Unit = {

    val blockSize = if (args.length > 2) args(2) else "4096"

    val spark = SparkSession
      .builder()
      .appName("Broadcast Test")
      .config("spark.broadcast.blockSize", blockSize)
      .config("spark.master", "local")
      .getOrCreate()

    val sc = spark.sparkContext

  val inputJsonData1 = ("""{"eventId":"7KAXzVFXi2ze6hnF","eventType":"MovementTypeUpdated","eventTime":"2018-07-10T23:12:16.67Z",
    "eventProducerId":"SupplyChain","eventVersion":"v1","specifications":[],"auditInfo":{"iamUniqueId":"1c157fee806211e8a4f70000008a3e0a"},
    "headerReference":{"activityId":"1c157fee806211e8a4f70000008a3e0a","applicationId":"SAP ECC","channelId":"Retail",
      "interactionId":"1c157fee806211e8a4f70000008a3e0a","sessionId":"1c157fee806211e8a4f70000008a3e0a"},
    "payload":{"InventoryMovement":{"movementType":"ReceivedInDc","movementDescription":"ReceivedInDc",
      "createdDate":"2018-07-10T23:12:16.633Z","materialMovement":[{"storeid":"8541","skuId":"190198451958",
      "amount":699.55,"quantity":1}]}}}""" :: Nil)

    println("abhijeet")
val a = sc.parallelize(inputJsonData1)

  a.collect.foreach(println)

    val df = spark.read.json(a)
df.printSchema()

/*
    |-- auditInfo: struct (nullable = true)
    |    |-- iamUniqueId: string (nullable = true)
    |-- eventId: string (nullable = true)
    |-- eventProducerId: string (nullable = true)
    |-- eventTime: string (nullable = true)
    |-- eventType: string (nullable = true)
    |-- eventVersion: string (nullable = true)
    |-- headerReference: struct (nullable = true)
    |    |-- activityId: string (nullable = true)
    |    |-- applicationId: string (nullable = true)
    |    |-- channelId: string (nullable = true)
    |    |-- interactionId: string (nullable = true)
    |    |-- sessionId: string (nullable = true)
    |-- payload: struct (nullable = true)
    |    |-- InventoryMovement: struct (nullable = true)
    |    |    |-- createdDate: string (nullable = true)
    |    |    |-- materialMovement: array (nullable = true)
    |    |    |    |-- element: struct (containsNull = true)
    |    |    |    |    |-- amount: double (nullable = true)
    |    |    |    |    |-- quantity: long (nullable = true)
    |    |    |    |    |-- skuId: string (nullable = true)
    |    |    |    |    |-- storeid: string (nullable = true)
    |    |    |-- movementDescription: string (nullable = true)
    |    |    |-- movementType: string (nullable = true)
    |-- specifications: array (nullable = true)
    |    |-- element: string (containsNull = true)
*/

    df.show(10,false)

    val inputJsonDate2 = sc.textFile("file:///D:/BigData/Freelance Work/Krishna Jaswant Spark DF/InputData/data2.json")

    val inputJsomDateDF = spark.read.json(inputJsonDate2)

    inputJsomDateDF.printSchema()

    println(inputJsomDateDF.count())


  }

}