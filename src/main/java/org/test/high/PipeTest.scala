package org.test.high
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
  def |:(obj: Any) = println(obj)
}
