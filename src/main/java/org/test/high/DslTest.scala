package org.test.high

import org.scalatest.Matchers
import org.scalatest.WordSpec

/***
 * dsl学习
 * 参考资料 @{link http://blog.csdn.net/lyrebing/article/details/20129355}
 * @author solq
 * */
object DslTest extends WordSpec with Matchers {

  "test" in {
    var dsl = new DslObject;
    //相当于 dsl.path() 调用 格式  obj valName params

    dsl path {

    } path {
      
    }url = "555"
    
  }
}

class DslObject {

  var url: String = ""

  def path(f: => Unit): DslObject = {
    (() => f)()
    this
  }

  def apply(f: DslObject): DslObject = f
}