package com.unitec.service

import com.unitec.dao.MembroDao
import com.unitec.model.Membro.Membro
import org.slf4j.LoggerFactory
import com.unitec.controller.Usuario
import com.unitec.model.Membro.Membros

object MembroService extends Services[Membro, Membros](MembroDao) {
  val logger = LoggerFactory.getLogger(getClass)
  def isMembroValido(usuario: Usuario): Boolean = {
    logger.info("Nova validação de usuario executada!")
    MembroDao.isValido(usuario.email, usuario.senha)
  }

}