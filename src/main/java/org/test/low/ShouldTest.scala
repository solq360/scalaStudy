package org.test.low

import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.WordSpec

/**
 * *
 * 单元测试
 * @author solq
 */
class Funest extends FunSuite {
  test("test1") {
    val ele = Array('x', 2, 3)
    assert(ele.length == 2)
  }

  test("test2") {
    val ele = Array('x', 2, 3)
    assert(ele.length == 3)
  }
}
/**
 * dsl风格
 * @author solq
 * **/
class SpecTest extends WordSpec with Matchers with BeforeAndAfter {
  before {
    info("before")
  }
  after {
    info("after")
  }
  "SpecTest" should {
    "test1" in {

    }
    "test2" in {

    }
  }
}