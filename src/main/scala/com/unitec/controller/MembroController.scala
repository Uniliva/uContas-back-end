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

class MembroController extends ScalatraServlet {
  implicit val formats = Serialization.formats(NoTypeHints)
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
    val membro = read[Membro](request.body)
    val result = MembroService.save(membro)
    if (result) write(Mensagens("INFO", "Membro Cadastrado com sucesso"))
    else write(Mensagens("ERROR", "Erro ao cadastrar membro"))
  }

  delete("/remove/:id") {
    val id = params("id")
    val result = MembroService.delete(id.toLong)
    if (result) write(Mensagens("INFO", "Membro deletado com sucesso"))
    else write(Mensagens("ERROR", "Erro ao deletar membro"))
  }

  post("/update") {
    val membro = read[Membro](request.body)
    val result = MembroService.update(membro)
    if (result) write(Mensagens("INFO", "Membro atualizado com sucesso"))
    else write(Mensagens("ERROR", "Erro ao atualizar membro"))
  }
}