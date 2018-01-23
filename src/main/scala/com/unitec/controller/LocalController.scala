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
  get("/all") {
    write(LocalService.getCompras())
  }

  get("/busca/:id") {
    val id = params("id")
    LocalService.getCompraPorId(id.toLong) match {
      case Some(e) => write(e)
      case None    => write(Mensagens("INFO", "Local não encontrado"))
    }
  }

  post("/new") {
    val compra = read[Local](request.body)
    val result = LocalService.setCompra(compra)
    if (result) write(Mensagens("INFO", "Local Cadastrado com sucesso"))
    else write(Mensagens("ERROR", "Erro ao cadastrar local"))
  }

  delete("/remove/:id") {
    val id = params("id")
    val result = LocalService.deleteCompra(id.toLong)
    if (result) write(Mensagens("INFO", "Local deletado com sucesso"))
    else write(Mensagens("ERROR", "Erro ao deletar Local"))
  }

  post("/update") {
    val compra = read[Local](request.body)
    val result = LocalService.updateCompra(compra)
    if (result) write(Mensagens("INFO", "Local Atualizada com sucesso"))
    else write(Mensagens("ERROR", "Erro ao atualizar local"))
  }
}