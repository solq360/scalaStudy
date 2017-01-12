package org.test.low

/***
 * 字符串学习
 * @author solq
 * **/
object StringTest extends App {
  val a = "aaaaaaaaaaaaa";
  val auto2str= s" 自动替换 ${this.a} $a ";
  val str = """ {"key":"value"} """
  println(str);
  println(auto2str);
}