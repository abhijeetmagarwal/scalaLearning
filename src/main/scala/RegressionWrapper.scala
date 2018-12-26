package com

import org.apache.spark.sql.SparkSession
object RegressionWrapper extends RegressionTrait {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName("SparkToHive")
      .config("spark.master", "local")
      //.enableHiveSupport()
      .getOrCreate()

    import  spark.implicits._

    val sc = spark.sparkContext
    //Setting log level to error
    sc.setLogLevel("ERROR")
    val sqlContext = spark.sqlContext


    val json1Rdd = sc.textFile("D:\\BigData\\Freelance Work\\Krishna Jaswant Spark DF\\InputData\\data1")
    val json1DF = spark.read.json(json1Rdd).select($"eventId",$"eventversion",$"eventproducerid",$"eventtype",$"eventtime",
      $"payload.InventoryMovement.createdDate"
    .alias("load_date")).show
   // println("Hi Rahul")
    //json1DF.printSchema()

    //creating json2

    val json2Rdd = sc.textFile("D:\\BigData\\Freelance Work\\Krishna Jaswant Spark DF\\InputData\\data2.json")
    val json2DF = spark.read.json((json2Rdd)).select("eventid","eventtype").show

    //json2DF.printSchema()

    //Creating DF from Metadata file

      val metadataRdd = sc.textFile("D:\\BigData\\Freelance Work\\Krishna Jaswant Spark DF\\InputData\\data_for_meta_tbl.dat_ref")
      val metaDataRes1Filter = metadataRdd.filter(rec => rec.contains("RIS1"))
      val metaDataRes1DF= metaDataRes1Filter.map(ele =>{
      val o = ele.split("\\|",-1)
      val hive_tbl_nm= o(3)
      var TD_tbl_nm = o(4)
        if(TD_tbl_nm.isEmpty)
          TD_tbl_nm=hive_tbl_nm
        else
          TD_tbl_nm= TD_tbl_nm+":-"+hive_tbl_nm
      (hive_tbl_nm,TD_tbl_nm)

    }).toDF("hive_tbl_nm","TD_tbl_nm")

    metaDataRes1DF.show(false)

    val tbName = "TRANSFER_ORDER_HEADER,TRANSFER_ORDER_ITEM"//metaDataRes1DF.select("hive_tbl_nm").toString()
    val finalQry=createUnionQuery(tbName);
    println(s"Query is $finalQry")


    //val metadataDF = spark.read.format("")

   // metadataDF.printSchema()
}

  override def createUnionQuery(str: String):String= {

    var finalQuery:String = {
      var query:String = "";
      if (str.contains(",")) {
        val tblArray = str.split(",")

        for (tbl <- tblArray) {
          query += "select distinct eventid, eventtype from INVENTORY." + tbl + "where src_date between from_date and to_date UNION "
        }
        if(query.endsWith("UNION")) query=query.take(query.length -6).mkString else query
      }
      else {
        query = "select distinct eventid, eventtype from INVENTORY." + str + "where src_date between from_date and to_date"
      }
      query

    }

    return finalQuery
  }
}