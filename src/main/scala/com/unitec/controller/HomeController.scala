package com.unitec.controller

import com.unitec.dao.{ OrcamentoDao, CompraDao, LocalDao, MembroDao }
import com.unitec.model.OrcamentoEntity.Orcamento
import com.unitec.service.MembroService

import org.slf4j.LoggerFactory

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.{ read, write }
import org.json4s.native.Serialization

import org.scalatra._
import org.scalatra.scalate.ScalateSupport

class HomeController() extends ScalatraServlet with CorsSupport{
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global
  val logger = LoggerFactory.getLogger(getClass)
  protected implicit lazy val jsonFormats: Formats = DefaultFormats.withBigDecimal

  implicit val formats = Serialization.formats(NoTypeHints)
  
  options("/*"){
    response.setHeader(
      "Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
  }
  
  post("/valida") {
    logger.warn("Validando usuario!")
    val usuario = parse(request.body).extract[Usuario]
    write(usuario.copy(isValido = MembroService.isMembroValido(usuario)))
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

case class Usuario(email: String, senha: String, val isValido: Boolean = false)