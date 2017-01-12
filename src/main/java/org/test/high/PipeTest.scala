package org.test.high

import org.test.middle.TestObject2
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Buffer

/**
 * *
 * pipe学习
 * @author solq
 */
object PipeTest extends App {

  def json(obj: Map[_, _]) = obj |: Json

  implicit def piping[T](t: T) = new {
    def |[R](f: T => R): R = f(t)
  }

  val data = Map("a" -> 1, "b" -> 2)
  data | json | println

  (data |: Json) |: Println
}

object Json {
  def |:(obj: Map[_, _]) = {
    val conc = obj.map { p => "  " + p._1 + ": " + p._2 }.mkString(",\n")
    "{\n" + conc + "\n}"
  }
}

object Println {
  def |:(obj: Any) = {

    if (obj.isInstanceOf[Array[_]]) {
      val ar = obj.asInstanceOf[Array[_]];
      print("[")
      for (v <- ar) {
        print(v + " , ")
      }
      print("]\n")
    } else if (obj.isInstanceOf[Buffer[_]]) {
      val ar = obj.asInstanceOf[Buffer[_]];
      print("[")

      for (v <- ar) {
        print(v + " , ")
      }
      print("]\n")

    } else {
      println(obj)
    }
  }
}
