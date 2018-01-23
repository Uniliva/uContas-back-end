package com.unitec.dao

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import com.unitec.model.Local.{ Locais, Local }

import slick.jdbc.MySQLProfile.api._
import slick.dbio.DBIOAction

object LocalDao extends GenericDao[Local] {

  implicit val tb = TableQuery[Locais]

  def findById(idx: Long): Option[Local] = {
    val action = tb.filter(_.id === idx).result
    val result = db.run(action)
    Await.result(result, Duration.Inf).toList.headOption
  }

  def save(obj: Local): Boolean = {
    try {
      val action = tb += obj
      val result = db.run(action)
      Await.result(result, Duration.Inf)
      true
    } catch {
      case e: Exception => false
    }
  }

  def delete(id: Long): Boolean = {
    try {
      val action = tb.filter(_.id === id).delete
      val result = db.run(action)
      Await.result(result, Duration.Inf)
      true
    } catch {
      case e: Exception => false
    }
  }

  def update(obj: Local): Boolean = {
    try {
      val action = tb.filter(_.id === obj.id).update(obj)
      val result = db.run(action)
      Await.result(result, Duration.Inf)
      true
    } catch {
      case e: Exception => false
    }
  }

  def createTable(): Unit = {
    val result = db.run(DBIOAction.seq(tb.schema.create))
    Await.result(result, Duration.Inf)
  }
  def dropTable(): Unit = {
    val result = db.run(DBIOAction.seq(tb.schema.drop))
    Await.result(result, Duration.Inf)
  }

}