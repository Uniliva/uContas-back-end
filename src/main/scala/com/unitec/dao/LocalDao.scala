package com.unitec.dao

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import com.unitec.model.Local.Locais
import com.unitec.model.Local.Local

import slick.jdbc.MySQLProfile.api.TableQuery
import slick.jdbc.MySQLProfile.api.columnExtensionMethods
import slick.jdbc.MySQLProfile.api.longColumnType
import slick.jdbc.MySQLProfile.api.queryDeleteActionExtensionMethods
import slick.jdbc.MySQLProfile.api.queryInsertActionExtensionMethods
import slick.jdbc.MySQLProfile.api.queryUpdateActionExtensionMethods
import slick.jdbc.MySQLProfile.api.streamableQueryActionExtensionMethods
import slick.jdbc.MySQLProfile.api.valueToConstColumn

object LocalDao extends GenericDao[Local] {

  implicit val tb = TableQuery[Locais]

  def findById(idx: Long): Local = {
    val action = tb.filter(_.id === idx).result
    val result = db.run(action)
    Await.result(result, Duration.Inf).toList.head
  }

  def save(obj: Local): Unit = {
    val action = tb += obj
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def delete(obj: Local): Unit = {
    val action = tb.filter(_.id === obj.id).delete
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }
  
  
  def update(obj: Local): Unit = {
    val action = tb.filter(_.id === obj.id).update(obj)
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

}