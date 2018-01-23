package com.unitec.dao

import scala.concurrent.Await
import scala.concurrent.duration.Duration

import com.unitec.model.Membro.Membro
import com.unitec.model.Membro.Membros

import slick.jdbc.MySQLProfile.api._

object MembroDao extends GenericDao[Membro, Membros](TableQuery[Membros]) {
  implicit val tb = TableQuery[Membros]
  def isValido(email: String, senha: String): Boolean = {
    val action = tb.filter(x => { x.email === email && x.senha === senha }).result
    val result = db.run(action)
    Await.result(result, Duration.Inf).toList.size > 0
  }
}