package org.test.middle

/**
 * *
 * trait 学习 可用切面
 * scala没有接口，只有trait 可以多个继承 用	with 关键词
 * @author solq
 */
object TraitTest extends App {
  var o: ITraitTestObject = new TraitTestImpl;
  o.doAction();

  //切面
  o = new TraitTestImpl with BeforeTraitTestObject;
  o.doAction();

}

class TraitTestImpl extends ITraitTestObject {
  def doAction() {
    println("doAction");
  }
}

trait ITraitTestObject {
  def doAction()
}

trait BeforeTraitTestObject extends ITraitTestObject {
  abstract override def doAction() {
    println("doAction before");
    super.doAction();
  }
}

