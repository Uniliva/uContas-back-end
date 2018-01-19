package com.unitec.controller


import com.unitec.dao.{ OrcamentoDao, CompraDao, LocalDao, MembroDao }
import com.unitec.model.OrcamentoEntity.Orcamento
import org.slf4j.LoggerFactory

import org.json4s.{ DefaultFormats, Formats }
import org.scalatra.json._
import com.unitec.service.MembroService
import org.scalatra._
import org.scalatra.scalate.ScalateSupport

class HomeController() extends ScalatraServlet with JacksonJsonSupport with ScalateSupport {
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global
  val logger = LoggerFactory.getLogger(getClass)
  protected implicit lazy val jsonFormats: Formats = DefaultFormats.withBigDecimal

  before() {
    contentType = formats("json")
  }

  post("/valida") {
    val email = params("email")
    val senha = params("senha")
    logger.warn("Buscando usuario valido!")
    val valido = MembroService.ehMembroValido(email, senha)
    Validador(email, senha, valido)
  }

  post("/teste") {
params("email")
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

  case class Validador(email: String, senha: String, valido: Boolean = false)

}


