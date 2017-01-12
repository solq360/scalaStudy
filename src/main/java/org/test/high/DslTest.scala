package org.test.high

import org.scalatest.Matchers
import org.scalatest.WordSpec

object DslTest extends WordSpec with Matchers {

  "test" in {
    var dsl = new DslObject;

    dsl path {
      a =>

    }

  }
}

class DslObject {
  def path(@deprecatedName('func) f: Any => Unit): DslObject = {
    f()
    this
  }

  def apply(f: DslObject): DslObject = f
}