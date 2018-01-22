package com.unitec.dao

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import com.unitec.model.OrcamentoEntity.Orcamento
import com.unitec.model.OrcamentoEntity.OrcamentoMembro
import com.unitec.model.OrcamentoEntity.OrcamentoMembros
import com.unitec.model.OrcamentoEntity.Orcamentos

import slick.jdbc.MySQLProfile.api._

import slick.jdbc.MySQLProfile.api._
import slick.dbio.DBIOAction

object OrcamentoDao extends GenericDao[Orcamento] {

  implicit val tb = TableQuery[Orcamentos]
  val tbOrcamemtoMembro = TableQuery[OrcamentoMembros]

  def findById(idx: Long): Orcamento = {
    val action = tb.filter(_.id === idx).result
    val result = db.run(action)
    Await.result(result, Duration.Inf).toList.head
  }

  def save(obj: Orcamento): Unit = {
    val action = tb += obj
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def delete(obj: Orcamento): Unit = {
    val action = tb.filter(_.id === obj.id).delete
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def update(obj: Orcamento): Unit = {
    val action = tb.filter(_.id === obj.id).update(obj)
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def AdicionaMembro(obj: OrcamentoMembro): Unit = {
    val action = tbOrcamemtoMembro += obj
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  def RemoveMembro(obj: OrcamentoMembro): Unit = {
    val action = tbOrcamemtoMembro.filter(t => t.orcamentoFK === obj.idOrcamento && t.membroFK === obj.idMembro).delete
    val result = db.run(action)
    Await.result(result, Duration.Inf)
  }

  /* def findMembros(obj: Orcamento): List<Membro> = {
    val action =  tbOrcamemtoMembro.filter(orcamentoFK == obj.id).result
    Await.result(result, Duration.Inf).toList.head
  }*/

  def createTable(): Unit = {
    val result = db.run(DBIOAction.seq(tb.schema.create))
    Await.result(result, Duration.Inf)
  }

  def dropTable(): Unit = {
    val result = db.run(DBIOAction.seq(tb.schema.drop))
    Await.result(result, Duration.Inf)
  }
  def createTableM(): Unit = {
    val result = db.run(DBIOAction.seq(tbOrcamemtoMembro.schema.create))
    Await.result(result, Duration.Inf)
  }

  def dropTableM(): Unit = {
    val result = db.run(DBIOAction.seq(tbOrcamemtoMembro.schema.drop))
    Await.result(result, Duration.Inf)
  }

}