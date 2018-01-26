package com.unitec.controller

import org.json4s.DefaultFormats
import org.json4s.Formats
import org.json4s.NoTypeHints
import org.json4s.jackson.JsonMethods.parse
import org.json4s.jackson.Serialization
import org.json4s.jvalue2extractable
import org.json4s.native.Serialization.write
import org.json4s.string2JsonInput
import org.scalatra.CorsSupport
import org.scalatra.ScalatraServlet
import org.slf4j.LoggerFactory

import com.unitec.dao._
import com.unitec.service.MembroService

class HomeController() extends ScalatraServlet with CorsSupport {
  
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global
  val logger = LoggerFactory.getLogger(getClass)
  protected implicit lazy val jsonFormats: Formats = DefaultFormats.withBigDecimal
  implicit val formats = Serialization.formats(NoTypeHints)

  options("/*") {
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
  }

  post("/valida") {
    logger.info("Validando usuario!")
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