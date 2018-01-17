package com.unitec.dao

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import br.com.devQueijo.model.Membro.Membro
import br.com.devQueijo.model.Membro.Membros
import slick.jdbc.MySQLProfile.api.TableQuery
import slick.jdbc.MySQLProfile.api.columnExtensionMethods
import slick.jdbc.MySQLProfile.api.longColumnType
import slick.jdbc.MySQLProfile.api.queryDeleteActionExtensionMethods
import slick.jdbc.MySQLProfile.api.queryInsertActionExtensionMethods
import slick.jdbc.MySQLProfile.api.queryUpdateActionExtensionMethods
import slick.jdbc.MySQLProfile.api.streamableQueryActionExtensionMethods
import slick.jdbc.MySQLProfile.api.valueToConstColumn

import slick.jdbc.MySQLProfile.api._
import slick.dbio.DBIOAction

object MembroDao extends GenericDao[Membro] {

  implicit val tb = TableQuery[Membros]

  def findById(idx: Long): Membro = {
    val action = tb.filter(_.id === idx).result
    val result = db.run(action)
    Await.result(result, Duration.Inf).toList.head
  }

  def save(obj: Membro): Unit = {
    val action = tb += obj
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def delete(obj: Membro): Unit = {
    val action = tb.filter(_.id === obj.id).delete
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def update(obj: Membro): Unit = {
    val action = tb.filter(_.id === obj.id).update(obj)
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def createTable(): Unit = {
    db.run(DBIOAction.seq(tb.schema.create))
  }
  def dropTable(): Unit = {
    db.run(DBIOAction.seq(tb.schema.drop))
  }
}