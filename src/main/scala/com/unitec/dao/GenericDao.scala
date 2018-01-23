package com.unitec.dao

import com.unitec.util.BaseUtil
import slick.jdbc.MySQLProfile.api._
import com.unitec.model.BaseEntity
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.unitec.model.BaseTables
import slick.dbio.DBIOAction

abstract class GenericDao[T <: BaseEntity, B <: BaseTables[T]](tb: TableQuery[B]) {
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  //conexão banco de dados
  def db: Database = BaseUtil.getInstance

  def findAll(): List[T] = {
    val action = tb.result
    val resp = db.run(action)
    //obriga a future a executar
    Await.result(resp, Duration.Inf).toList.asInstanceOf[List[T]]
  }

  def findById(idx: Long): Option[T] = {
    val action = tb.filter(_.id === idx).result
    val result = db.run(action)
    val retorno = Await.result(result, Duration.Inf).toList
    retorno.headOption
  }

  def save(obj: T): Boolean = {
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

  def update(obj: T): Boolean = {
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