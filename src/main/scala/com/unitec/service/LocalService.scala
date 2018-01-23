package com.unitec.service
import org.slf4j.LoggerFactory
import com.unitec.model.Local.Local
import com.unitec.dao.LocalDao

object LocalService {
  val logger = LoggerFactory.getLogger(getClass)
  def getCompras(): List[Local] = {
    logger.info("Buscando todas os Locais")
    LocalDao.findAll()
  }
  def getCompraPorId(id: Long): Option[Local] = {
    logger.info("Buscando um local especifico")
    LocalDao.findById(id)
  }
  def setCompra(local: Local): Boolean = {
    logger.info("Salvando novo local")
    LocalDao.save(local)
  }

  def deleteCompra(id: Long): Boolean = {
    logger.info("Deletando Local")
    LocalDao.delete(id)
  }

  def updateCompra(local: Local): Boolean = {
    logger.info("Atualizando Local")
    LocalDao.update(local)
  }
}