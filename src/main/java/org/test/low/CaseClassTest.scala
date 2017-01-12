package org.test.low

import scala.beans.BeanProperty

/**
 * <p>var 属性权限可读写 </p>
 * <p>val 属性权限可读不能写 相当于 java final</p>
 * <p>小结 : case class 比较适合做上下文临时对象</p>
 * @author solq
 */
case class QuerySpark(

  /** sql dsl */
  @BeanProperty val sql: String,

  /** connect options */
  val options: Map[String, String],

  /** option tmp table */
  val tmpTable: String)

sealed abstract class Event(name: String);
sealed abstract class PlayerEvent(id: Long, name: String) extends Event(name);

case class PlayerLoginEvent(id: Long, name: String, time: Long) extends PlayerEvent(id, name);
case class PlayerRegisterEvent(id: Long, name: String, time: Long) extends PlayerEvent(id, name);


sealed abstract class Log(name: String);
sealed abstract class PlayerLog(id: Long, account:String, roleName :String,name: String) extends Log(name);

case class PlayerRegisterLog(time:Long,id: Long, account:String, roleName :String,name: String) extends 
PlayerLog(id: Long, account:String, roleName :String,name: String);


//abstract class Box[+A]{ def foo(): A }
//abstract class Box[-A]{ def foo(a: A) }
//abstract class Box[+A]{ def foo[B >: A](b: B) }
//abstract class Box[-A]{ def foo[B <: A](): B}
