package org.test.middle

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.test.low.QuerySpark

/**
 * json学习 
 * 使用jackson库
 * @author solq
 */
object JsonTest extends App {
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  val json = """  {"sql":"select * from account"}  """;
  val querySpark: QuerySpark = mapper.readValue(json, classOf[QuerySpark]);

  println(querySpark.sql);

}