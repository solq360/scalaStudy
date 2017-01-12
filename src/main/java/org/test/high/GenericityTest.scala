package org.test.high

import java.util.ArrayList
import java.lang.Long
import java.lang.Double
import org.scalatest.FunSuite
import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag
import scala.collection.immutable.Stack

/**
 * *
 * 泛型学习
 * @author solq
 */
class GenericityTest extends FunSuite {

  test("UpperBounds") {
    var u1 = new UpperBounds[Long];
    var u2 = new UpperBounds[Double];
    u1.push(1);

    u2.push(1);
    u2.push(1d);
    u2.push(1f);
  }

  test("LowerBounds") {
    var u1 = new LowerBounds[Number];
    var u2 = new LowerBounds[Double];
    u1.push(1);
    u2.push(1);
    u2.push(1f);
    u2.push(1d);

  }

  test("UpperAndLowerBounds") {
    var u1 = new UpperAndLowerBounds(1, 2);
    var u2 = new UpperAndLowerBounds[String]("xxx", "bbb")

  }
  test("other") {
    var other = new OtherGenericity;

    other.manifest(1, 2) |: Println
    other.manifest(2, "x") |: Println

    other.mkArray(1, 2) |: Println
    other.mkArray("xx", "ere") |: Println
    other.mkArray(2, "x") |: Println

    
    
    var obj1 = new CoVariant(new ClassA);
    var obj2 = new CoVariant(new SuperClass);
    var obj3: CoVariant[SuperClass] = obj1;
    //var obj4: CoVariant[ClassA] = obj2; error
    
  }
}

/** 上界(upper bound) 操作符  <: [ 抽象子类 <: 条件类型 ] **/
class UpperBounds[T <: Number] {
  var list: ArrayBuffer[T] = new ArrayBuffer[T]
  def push(n: T) {
    list += n
  }
}
/** 下界(Lower bound) 操作符  >: [ 抽象父类 >: 条件类型 ] **/
class LowerBounds[T >: Double] {
  var list: ArrayBuffer[T] = new ArrayBuffer[T]

  def push(n: T) {
    list += n
  }
}
/**
 * 上下界(UpperAndLower bound)
 *  Ordered[T]:为T下界，T:为Ordered[T]上界
 *  操作符  <% [ 本身类 <% 条件类型 ] *
 */
class UpperAndLowerBounds[T <% Ordered[T]](val first: T, val second: T) {
  def bigger = if (first.compareTo(second) > 0) first else second
}

class OtherGenericity {
  /**Manifest 任意类型**/
  def manifest[T: Manifest](t1: T, t2: T) = {
    var ret = Array(t1, t2)
    ret;
  }

  def mkArray[T: ClassTag](elems: T*) = Array[T](elems: _*)

}

/**
 * ***********************************************************************
 *	所谓 协变跟逆变 只是解决 泛型 T 声明报错，跟 上下界配合解决
 * 
 * 协变(co-variant)和逆变(contra-variant)
 * ***********************************************************************
 * 
 * 
 *
 * +B是B的超集，叫协变 Covariant
 * 		示例:
 * 				ClassA extends ClassB
 * 				Covariant[ClassA] 是 Covariant[ClassB] 的子类
 * -A是A的子集，叫逆变 
 *		示例:
 * 				ClassA extends ClassB
 * 				ContraVariant[ClassB] 是 ContraVariant[ClassA] 的子类
 */


private class CoVariant[+A](t:A)
private class ContraVariant[-A](t:A)

private class SuperClass;
private class ClassA extends SuperClass;

private abstract class Consumer[-S,+T](){
  def m1[U >: T](u: U)//协变，下界
  def m2[U <: S](s: S)//逆变，上界
}

///////////////多重定义//////////////////


/* 
    // 表示：A和B为T上界 
    T <: A with B 
     
    // 表示：A和B为T下界 
    T >: A with B 
     
    // 表示：同时拥有上界和下界，并且A为下界，B为上界，A为B的子类，顺序不能颠倒。 
    T >: A <: B 
     
    // 表示：类型变量界定，即同时满足AT这种隐式值和BT这种隐式值 
    T:A:B 
     
    // 表示：视图界定，即同时能够满足隐式转换的A和隐式转换的B 
    T <% A <% B  
  */
    /**
     * *
     *
     * obj.isInstanceOf[Class] obj instanceof Class
     *
     * obj.asInstanceOf[Class] (Class)obj
     *
     * classOf[Class] Class.class
     */
 
