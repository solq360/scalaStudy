package org.test.middle

/**
 * 隐式学习
 * @author solq
 */
class Worker {
  def test(s: String) = println("正在工作:" + s)
}
object Proxy {
  def exec(implicit iVar: String) {
    println(iVar);
  }
  def run(input: String): Unit = println(input)
  //重载参数
  implicit def run1(t: TestObject) = "重载啦"

  //通过包导入当前对象自动继承 右边值对象
  implicit def i2p(t: TestObject) = new Worker()
  implicit def i2p2(t: TestObject2) = new Worker()
}

class TestObject {

}

class TestObject2 {

}

object TmplicitTest extends App {
  //相当于导入 Proxy implicit 所有属性同方法
  import Proxy._
  val o = new TestObject;
  o.test("test");

  val o2 = new TestObject2;
  o2.test("test2");

  run("start")
  run(o)

  implicit val iv = "iv";
  val iv2 = "iv3";
  exec;
}


 
 