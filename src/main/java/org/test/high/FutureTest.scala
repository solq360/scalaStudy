package org.test.high

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.Promise
import scala.async.Async.{ async, await }

/**
 * Future学习
 * @author solq
 */
object FutureTest extends App {

  def doWork(i: Int) = {
    Thread.sleep(500);
    i
  }

  //////////////////////////////////////////////
  (1 to 5) foreach { index =>
    val future = Future(doWork(index))

    future onSuccess {
      case ret: Int => println(s"Success! returned: $ret")
    }
    future onFailure {
      case th: Throwable => println(s"Failure! returned: $th")
    }
  }

  var st = System.currentTimeMillis();

  ////////////阻塞多个Future///////////
  val one = Future(doWork(1))
  val two = Future(doWork(15))
  val ret = for {
    tone <- one
    ttwo <- two
  } yield (tone + ttwo)

  ret onSuccess {
    case s: Int =>
      println("ret : " + s)
      var et = System.currentTimeMillis() - st;
      println(s"ret run time : $et");
  }

  var st3 = System.currentTimeMillis();

  //合并Future
  val ret3 = Future(doWork(9999)) zip Future(doWork(88)) zip Future(doWork(1)) zip Future(doWork(20)) zip Future(doWork(30))
  ret3 onSuccess {
    case s: ((((Int, Int), Int), Int), Int) =>
      println(s)
      var et = System.currentTimeMillis() - st3;
      println(s"ret3 run time : $et");
  }

  ////////////////Await组合/////////////////////
  val result = Promise[Int]
  try {
    val v1 = Await.result(Future(doWork(9999)), Duration.Inf)
    val v2 = Await.result(Future(doWork(9999)), Duration.Inf)
    val v3 = Await.result(Future(doWork(9999)), Duration.Inf)
    result.success(v3 + v2 + v1)
  } catch {
    case t: Throwable => result.failure(t)
  }
  result.future onSuccess {
    case s: Int =>
      println(s)
  }

  ///////////////async 库简化//////////////////
  var ret4 = async {
    val v1 = await(Future(doWork(9999)))
    val v2 = await(Future(doWork(9999)))
    val v3 = await(Future(doWork(9999)))
    v1 + v2 + v3
  }
  ret4 onSuccess {
    case s: Int =>
      println(s)
  }
  Thread.sleep(5000);

}