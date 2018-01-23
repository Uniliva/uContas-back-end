package com.unitec.service

import com.unitec.dao.OrcamentoDao
import com.unitec.model.OrcamentoEntity.Orcamentos
import com.unitec.model.OrcamentoEntity.Orcamento

object OrcamentoService extends Services[Orcamento,Orcamentos](OrcamentoDao) {
  
}