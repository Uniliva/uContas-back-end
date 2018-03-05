package com.unitec.controller

import org.scalatra.ScalatraServlet
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.{ read, write }
import org.json4s.native.Serialization
import org.slf4j.LoggerFactory
import com.unitec.model.Local.Local
import com.unitec.dao.LocalDao
import com.unitec.service.LocalService
import com.unitec.model.Mensagens

class LocalController extends ScalatraServlet {
  implicit val formats = Serialization.formats(NoTypeHints)
  val logger = LoggerFactory.getLogger(getClass)
  before() {
    response.setHeader("Access-Control-Allow-Origin", "*")
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS")
    response.setHeader("Access-Control-Max-Age", "3600")
  }
  get("/all") {
    write(LocalService.getAll())
  }

  get("/busca/:id") {

    val id = params("id")
    LocalService.getPorId(id.toLong) match {
      case Some(e) => write(e)
      case None    => write(Mensagens("INFO", "Local não encontrado"))
    }
  }

  post("/new") {
    logger.info("Criando local")
    try {
      val local = read[Local](request.body)
      val result = LocalService.save(local)
      if (result) write(Mensagens("INFO", "Local Cadastrado com sucesso"))
      else halt(400)
    } catch {
      case e: Exception => halt(400)
    }
  }

  get("/remove/:id") {
    logger.info("Apagando Local")
    try {
      val id = params("id")
      val result = LocalService.delete(id.toLong)
      if (result) write(Mensagens("INFO", "Local deletado com sucesso"))
      else halt(400)
    } catch {
      case e: Exception => halt(400)
    }
  }

  post("/update") {
    try {
      val local = read[Local](request.body)
      println(local)
      val result = LocalService.update(local)
      if (result) write(Mensagens("INFO", "Local Atualizada com sucesso"))
      else halt(400)
    } catch {
      case e: Exception => println(e.fillInStackTrace())
    }
  }
}