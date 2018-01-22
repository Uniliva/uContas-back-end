package com.unitec.controller

import com.unitec.dao.{ OrcamentoDao, CompraDao, LocalDao, MembroDao }
import com.unitec.model.OrcamentoEntity.Orcamento
import com.unitec.service.MembroService

import org.slf4j.LoggerFactory

import org.json4s._
import org.json4s.jackson.JsonMethods._

import org.scalatra._
import org.scalatra.scalate.ScalateSupport

class HomeController() extends ScalatraServlet with ScalateSupport {
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global
  
  val logger = LoggerFactory.getLogger(getClass)
  
  implicit val formats = DefaultFormats
  
  post("/valida") {
    val email = params("email")
    val senha = params("senha")
    logger.warn("Buscando usuario valido!")
    val valido = MembroService.ehMembroValido(email, senha)
    Validador(email, senha, valido)
  }

  post("/teste") {
    val juser = parse(request.body)
    println(juser)
    val u = juser.extract[user]
    println("--"+u)
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
  case class user(email: String, senha: String)

}


