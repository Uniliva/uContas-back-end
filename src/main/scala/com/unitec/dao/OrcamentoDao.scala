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

object OrcamentoDao extends GenericDao[Orcamento,Orcamentos](TableQuery[Orcamentos]) {

  implicit val tb = TableQuery[Orcamentos]
  val tbOrcamemtoMembro = TableQuery[OrcamentoMembros]
  

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
  
  def createTableM(): Unit = {
    val result = db.run(DBIOAction.seq(tbOrcamemtoMembro.schema.create))
    Await.result(result, Duration.Inf)
  }

  def dropTableM(): Unit = {
    val result = db.run(DBIOAction.seq(tbOrcamemtoMembro.schema.drop))
    Await.result(result, Duration.Inf)
  }

}