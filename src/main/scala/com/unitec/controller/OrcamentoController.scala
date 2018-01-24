package com.unitec.controller

import org.scalatra.ScalatraServlet
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.{ read, write }
import org.json4s.native.Serialization
import org.slf4j.LoggerFactory
import com.unitec.service.OrcamentoService
import com.unitec.model.Mensagens
import com.unitec.model.OrcamentoEntity.Orcamento
import com.unitec.model.OrcamentoEntity.OrcamentoMembro

class OrcamentoController extends ScalatraServlet {
  implicit val formats = Serialization.formats(NoTypeHints)
  get("/all") {
    write(OrcamentoService.getAll())
  }

  get("/busca/:id") {
    val id = params("id")
    OrcamentoService.getPorId(id.toLong) match {
      case Some(e) => write(e)
      case None    => write(Mensagens("INFO", "Orçamento não encontrado"))
    }
  }

  post("/new") {
    val orcamento = read[Orcamento](request.body)
    val result = OrcamentoService.save(orcamento)
    if (result) write(Mensagens("INFO", "Orçamento Cadastrado com sucesso"))
    else write(Mensagens("ERROR", "Erro ao cadastrar Orçamento"))
  }

  delete("/remove/:id") {
    val id = params("id")
    val result = OrcamentoService.delete(id.toLong)
    if (result) write(Mensagens("INFO", "Orçamento deletado com sucesso"))
    else write(Mensagens("ERROR", "Erro ao deletar Orçamento"))
  }

  post("/update") {
    val orcamento = read[Orcamento](request.body)
    val result = OrcamentoService.update(orcamento)
    if (result) write(Mensagens("INFO", "Orçamento atualizado com sucesso"))
    else write(Mensagens("ERROR", "Erro ao atualizar orçamento"))
  }

  get("/lista-membros/:idOrcamento") {
    val id = params("idOrcamento")
    write(OrcamentoService.listarMembros(id.toLong))
  }
  
  post("/adiciona-membro") {
    val orcamentoMembro = read[OrcamentoMembro](request.body)
    val result = OrcamentoService.adicionarMembro(orcamentoMembro)
    if (result) write(Mensagens("INFO", "Membro adicionando ao orçamento"))
    else write(Mensagens("ERROR", "Erro ao  adicionar ao orçamento"))
  }  
    
  post("/remove-membro") {
    val orcamentoMembro = read[OrcamentoMembro](request.body)
    val result = OrcamentoService.removeMembro(orcamentoMembro)
    if (result) write(Mensagens("INFO", "Membro removido do orçamento"))
    else write(Mensagens("ERROR", "Erro ao  remover do orçamento"))
  }

}