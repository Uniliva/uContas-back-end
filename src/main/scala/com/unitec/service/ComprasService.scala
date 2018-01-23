package com.unitec.service

import org.slf4j.LoggerFactory
import com.unitec.model.Compra.Compra
import com.unitec.dao.CompraDao
import com.unitec.model.Compra

object ComprasService {
  val logger = LoggerFactory.getLogger(getClass)
  def getCompras(): List[Compra] = {
    logger.info("Buscando todas as contas")
    CompraDao.findAll()
  }
  def getCompraPorId(id: Long): Option[Compra] = {
    logger.info("Buscando uma conta especifica")
    CompraDao.findById(id)
  }
  def setCompra(compra: Compra): Boolean = {
    logger.info("Salvando nova compra")
    CompraDao.save(compra)
  }

  def deleteCompra(id: Long): Boolean = {
    logger.info("Deletando compra")
    CompraDao.delete(id)
  }
  
   def updateCompra(compra: Compra): Boolean = {
    logger.info("Atualizando nova compra")
    CompraDao.update(compra)
  }
}