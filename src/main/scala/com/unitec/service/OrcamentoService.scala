package com.unitec.service

import com.unitec.dao.OrcamentoDao
import com.unitec.model.Membro.Membro
import com.unitec.model.OrcamentoEntity.Orcamento
import com.unitec.model.OrcamentoEntity.OrcamentoMembro
import com.unitec.model.OrcamentoEntity.Orcamentos

object OrcamentoService extends Services[Orcamento, Orcamentos](OrcamentoDao) {

  def adicionarMembro(orcamentoMembro: OrcamentoMembro): Boolean = {
    OrcamentoDao.adicionaMembro(orcamentoMembro)
  }

  def removeMembro(orcamentoMembro: OrcamentoMembro): Boolean = {
    OrcamentoDao.removeMembro(orcamentoMembro)
  }

  def listarMembros(idOrcamento: Long): List[Membro] = {
    OrcamentoDao.getMembros(idOrcamento)
  }
}