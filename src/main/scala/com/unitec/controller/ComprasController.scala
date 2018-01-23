package com.unitec.controller

import org.scalatra.ScalatraServlet
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.{ read, write }
import org.json4s.native.Serialization
import com.unitec.service.ComprasService
import com.unitec.model.Compra.Compra
import com.unitec.model.Mensagens

class ComprasController extends ScalatraServlet {
  implicit val formats = Serialization.formats(NoTypeHints)
  get("/all") {
    write(ComprasService.getCompras())
  }

  get("/busca/:id") {
    val id = params("id")
    ComprasService.getCompraPorId(id.toLong) match {
      case Some(e) => write(e)
      case None => write(Mensagens("INFO","Compra não encontrada"))
    }
  }

  post("/new") {
    val compra = read[Compra](request.body)
    val result = ComprasService.setCompra(compra)
    if (result) write(Mensagens("INFO", "Compra Cadastrada com sucesso"))
    else write(Mensagens("ERROR", "Erro ao cadastrar compra"))
  }

  delete("/remove/:id") {
    val id = params("id")
    val result = ComprasService.deleteCompra(id.toLong)
    if (result) write(Mensagens("INFO", "Compra deletada com sucesso"))
    else write(Mensagens("ERROR", "Erro ao deletar compra"))
  }

  post("/update") {
    val compra = read[Compra](request.body)
    val result = ComprasService.updateCompra(compra)
    if (result) write(Mensagens("INFO", "Compra Atualizada com sucesso"))
    else write(Mensagens("ERROR", "Erro ao atualizar compra"))
  }
}