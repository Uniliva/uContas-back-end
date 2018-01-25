package com.unitec.controller

import org.scalatra.ScalatraServlet
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.{ read, write }
import org.json4s.native.Serialization
import com.unitec.service.ComprasService
import com.unitec.model.Compra.Compra
import com.unitec.model.Mensagens
import org.scalatra.CorsSupport
import org.slf4j.LoggerFactory

class ComprasController extends ScalatraServlet with CorsSupport {
  implicit val formats = Serialization.formats(NoTypeHints)
  val logger = LoggerFactory.getLogger(getClass)
  
  options("/*") {
    logger.warn("------------ response -------------" + response)
    response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"))
  }

  get("/all") {
    write(ComprasService.getAll())
  }

  get("/busca/:id") {
    val id = params("id")
    ComprasService.getPorId(id.toLong) match {
      case Some(e) => write(e)
      case None    => write(Mensagens("INFO", "Compra não encontrada"))
    }
  }

  post("/new") {
    val compra = read[Compra](request.body)
    val result = ComprasService.save(compra)
    if (result) write(Mensagens("INFO", "Compra Cadastrada com sucesso"))
    else write(Mensagens("ERROR", "Erro ao cadastrar compra"))
  }

  delete("/remove/:id") {
    val id = params("id")
    val result = ComprasService.delete(id.toLong)
    if (result) write(Mensagens("INFO", "Compra deletada com sucesso"))
    else write(Mensagens("ERROR", "Erro ao deletar compra"))
  }

  post("/update") {
    val compra = read[Compra](request.body)
    val result = ComprasService.update(compra)
    if (result) write(Mensagens("INFO", "Compra Atualizada com sucesso"))
    else write(Mensagens("ERROR", "Erro ao atualizar compra"))
  }
}