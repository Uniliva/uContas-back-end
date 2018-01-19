package com.unitec.dao

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import slick.jdbc.MySQLProfile.api._
import slick.dbio.DBIOAction
import com.unitec.model.Compra
import com.unitec.model.Compra.Compras
import com.unitec.model.Compra.Compra

object CompraDao extends GenericDao[Compra] {

  implicit val tb = TableQuery[Compras]

  def findById(idx: Long): Compra = {
    val action = tb.filter(_.id === idx).result
    val result = db.run(action)
    Await.result(result, Duration.Inf).toList.head
  }
  


  def save(obj: Compra): Unit = {
    val action = tb += obj
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def delete(obj: Compra): Unit = {
    val action = tb.filter(_.id === obj.id).delete
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def update(obj: Compra): Unit = {
    val action = tb.filter(_.id === obj.id).update(obj)
    val result = db.run(action)
    Await.result(result, Duration.Inf)
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