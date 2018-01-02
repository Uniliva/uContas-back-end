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
import br.com.devQueijo.model.Compra.Compra
import br.com.devQueijo.model.Compra.Compras

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

}