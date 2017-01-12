package org.test.low

/***
 * for学习
 * @author solq
 * */
object ForTest {
  def main(args: Array[String]) {
    var x = 0;
    var y = 0;
    //多重循环
    for (y <- 1 to 3; x <- 1 to 5) {
      println("Value y: " + y);
      println("Value of x: " + x);
    }

    //filter
    println("==============filter==================");
    val numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    for (a <- numList if a % 3 == 0; if a < 8) {
      println("filter of a: " + a);
    }
    //yield 返回集合
    println("==============yield==================");

    var retVal = for {
      a <- numList
      if a != 3; if a < 8
    } yield a

    for (a <- retVal) {
      println("yield of a: " + a);
    }
    
    val colors = Map("red" -> "#FF0000", "azure" -> "#F0FFFF")
    println("==============map==================");

    var o = for{
      (k, v) <- colors
     } yield k
    
    for (a <- o) {
      println("yield of a: " + a);
    }
  }
}