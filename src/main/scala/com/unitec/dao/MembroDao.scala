package com.unitec.dao

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import slick.dbio.DBIOAction
import slick.jdbc.MySQLProfile.api._
import com.unitec.model.Membro.{ Membro, Membros }
import org.slf4j.LoggerFactory

object MembroDao extends GenericDao[Membro] {

  val logger = LoggerFactory.getLogger(getClass)

  implicit val tb = TableQuery[Membros]

  def findById(idx: Long): Membro = {
    val action = tb.filter(_.id === idx).result
    val result = db.run(action)
    Await.result(result, Duration.Inf).toList.head
  }
  def findByEmail(email: String): Membro = {
    val action = tb.filter(_.email === email).result
    val result = db.run(action)
    Await.result(result, Duration.Inf).toList.head
  }

  def isValido(email: String, senha: String): Boolean = {
    val action = tb.filter(x => {x.email === email && x.senha === senha}).result
    val result = db.run(action)
    Await.result(result, Duration.Inf).toList.size > 0
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
    val result = db.run(DBIOAction.seq(tb.schema.create))
    Await.result(result, Duration.Inf)
  }
  def dropTable(): Unit = {
    val result = db.run(DBIOAction.seq(tb.schema.drop))
    Await.result(result, Duration.Inf)
  }
}