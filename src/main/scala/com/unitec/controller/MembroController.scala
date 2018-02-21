package com.unitec.controller

import com.unitec.service.MembroService
import org.scalatra.ScalatraServlet
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.{ read, write }
import org.json4s.native.Serialization
import org.slf4j.LoggerFactory
import com.unitec.model.Mensagens
import com.unitec.model.Membro.Membro
import org.scalatra.MethodOverride

class MembroController extends ScalatraServlet with MethodOverride {
  implicit val formats = Serialization.formats(NoTypeHints)
  val logger = LoggerFactory.getLogger(getClass)
  before() {
    response.setHeader("Access-Control-Allow-Origin", "*")
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS")
    response.setHeader("Access-Control-Max-Age", "3600")
  }
  get("/all") {
    write(MembroService.getAll())
  }

  get("/busca/:id") {
    val id = params("id")
    MembroService.getPorId(id.toLong) match {
      case Some(e) => write(e)
      case None    => write(Mensagens("INFO", "Membro não encontrado"))
    }
  }

  post("/new") {
    logger.info("Criando membro")
    try {
      val membro = read[Membro](request.body)
      val result = MembroService.save(membro)
      if (result)
        write(Mensagens("INFO", "Membro Cadastrado com sucesso"))
      else halt(400)
    } catch {
      case e: Exception => halt(400)
    }
  }

  get("/remove/:id") {
    logger.info("Apagando membro")
    try {
      val id = params("id")
      val result = MembroService.delete(id.toLong)
      if (result) write(Mensagens("INFO", "Membro deletado com sucesso"))
      else halt(400)
    } catch {
      case e: Exception => halt(400)
    }
  }

  post("/update") {
    try {
      val membro = read[Membro](request.body)
      val result = MembroService.update(membro)
      if (result) write(Mensagens("INFO", "Membro atualizado com sucesso"))
      else halt(400)
    } catch {
      case e: Exception => halt(400)
    }
  }
}