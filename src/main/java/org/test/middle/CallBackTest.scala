package org.test.middle

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import org.scalatest.Matchers
import org.scalatest.WordSpec

/**
 * *
 * 回调学习
 * @author solq
 * *
 */
class CallBackTest extends WordSpec with Matchers {

  "test1" in {
    val o = new CallBackObject;
    o.draw("a") {
      str =>
        println(str);
    }
  }

  "test2" in {
    val o = new CallBackObject;
    o.lock { () =>
      println("cb");
    }
  }

  "test3" in {
    val o = new CallBackObject;
    o.request({ () =>
      println("ok");
      throw new RuntimeException();
    }, { e =>
      e.printStackTrace();
    })
  }
}

class CallBackObject {
  private val lock: Lock = new ReentrantLock;

  def draw(offset: String)(cb: String => Unit) =
    cb(s"draw(offset = $offset), ${this.toString}")

  def lock(cb: () => Unit) {
    lock.lock();
    try {
      println("before");
      cb();
    } finally {
      lock.unlock();
    }
  }

  def request(cb: () => Unit, errorCb: Exception => Unit) {
    try {
       cb();
    } catch {
      case t: Exception => errorCb(t);
    }
  }
}