package com.unitec.dao

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import slick.jdbc.MySQLProfile.api._
import slick.dbio.DBIOAction
import com.unitec.model.Compra
import com.unitec.model.Compra.Compras
import com.unitec.model.Compra.Compra
import scala.util.control.Exception.Catch
import scala.util.Try

object CompraDao extends GenericDao[Compra] {

  implicit val tb = TableQuery[Compras]

  def findById(idx: Long): Option[Compra] = {
    val action = tb.filter(_.id === idx).result
    val result = db.run(action)
    val retorno = Await.result(result, Duration.Inf).toList
    retorno.headOption
  }

  def save(obj: Compra): Boolean = {
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

  def update(obj: Compra): Boolean = {
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