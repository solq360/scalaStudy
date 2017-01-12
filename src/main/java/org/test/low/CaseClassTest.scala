package org.test.low

import scala.beans.BeanProperty

/**
 * <p>var 属性权限可读写 </p>
 * <p>val 属性权限可读不能写 相当于 java final</p>
 * <p>lazy 相当于java double check </p>
 * <p>def 每次重新计算 相当于 java fun </p>
 * <p>scala 是种计算值语言 左边的值 = 右边的表达式 一些关键词声明只不过添加相应的操作权限 </p>
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
