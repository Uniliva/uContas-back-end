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
import com.unitec.model.OrcamentoEntity.Orcamentos
import com.unitec.model.Membro.Membro

object OrcamentoDao extends GenericDao[Orcamento, Orcamentos](TableQuery[Orcamentos]) {

  implicit val tb = TableQuery[Orcamentos]
  val tbOrcamemtoMembro = TableQuery[OrcamentoMembros]

  def adicionaMembro(obj: OrcamentoMembro): Boolean = {
    try {
      val action = tbOrcamemtoMembro += obj
      val result = db.run(action)
      Await.result(result, Duration.Inf)
      true
    } catch {
      case e: Exception => false
    }
  }

  def getMembros(idOrcamento: Long): List[Membro] = {
    val action = tbOrcamemtoMembro.filter(t => t.orcamentoFK === idOrcamento).result
    val result = db.run(action)
    val lista = Await.result(result, Duration.Inf).toList
    lista.map(r => {
      MembroDao.findById(r.idMembro) match {
        case e => e.head
      }
    })
  }

  def removeMembro(obj: OrcamentoMembro): Boolean = {
    try {
      val action = tbOrcamemtoMembro.filter(t => t.orcamentoFK === obj.idOrcamento && t.membroFK === obj.idMembro).delete
      val result = db.run(action)
      Await.result(result, Duration.Inf)
      true
    } catch {
      case e: Exception => false
    }
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