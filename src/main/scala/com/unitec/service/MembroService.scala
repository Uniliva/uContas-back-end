package com.unitec.service

import com.unitec.dao.MembroDao
import com.unitec.model.Membro.Membro
import org.slf4j.LoggerFactory


object MembroService {
val logger = LoggerFactory.getLogger(getClass)
  def ehMembroValido(email: String, senha: String): Boolean = {
    logger.info("Nova validação de usuario executada!")
    MembroDao.eValido(email, senha)
  }
  
}