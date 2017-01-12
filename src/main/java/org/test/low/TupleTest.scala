package org.test.low
/**
 * *
 * 元组学习
 * @author solq
 */
object TupleTest {
  def main(args: Array[String]): Unit = {
    var t = (1, "xxx", Array(1, 2, 3));
    println(t._1);
    println(t._2);
    println(t._3);

  }
}