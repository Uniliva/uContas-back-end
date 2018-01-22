package com.unitec.controller

import com.unitec.dao.{ OrcamentoDao, CompraDao, LocalDao, MembroDao }
import com.unitec.model.OrcamentoEntity.Orcamento
import com.unitec.service.MembroService

import org.slf4j.LoggerFactory

import org.json4s._
import org.json4s.jackson.JsonMethods._

import org.scalatra._
import org.scalatra.scalate.ScalateSupport
import org.scalatra.json.JacksonJsonSupport

class HomeController() extends ScalatraServlet with JacksonJsonSupport {
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global
  val logger = LoggerFactory.getLogger(getClass)
  protected implicit lazy val jsonFormats: Formats = DefaultFormats.withBigDecimal

  before() {
    contentType = formats("json")
  }

  post("/valida") {
    logger.warn("Validando usuario!")
    val usuario = parse(request.body).extract[Usuario]
    usuario.copy(isValido=MembroService.isMembroValido(usuario) )
  }

  get("/createTables") {
    logger.warn("Criando tablelas no banco de dados! -")
    LocalDao.createTable()
    MembroDao.createTable()
    OrcamentoDao.createTable()
    OrcamentoDao.createTableM()
    CompraDao.createTable()
    "tabelas criadas"
  }

}

case class Usuario(email: String, senha: String, val isValido:Boolean =false)