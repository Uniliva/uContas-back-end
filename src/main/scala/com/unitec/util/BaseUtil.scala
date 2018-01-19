package com.unitec.util
import org.slf4j.LoggerFactory
import com.mchange.v2.c3p0.ComboPooledDataSource

import slick.jdbc.MySQLProfile.api._

object BaseUtil {
  val logger = LoggerFactory.getLogger(getClass)
  val cpds = new ComboPooledDataSource

  private var instancia: Database = _

  def conection() = {
    logger.info(": - Nova Conecção com o banco Criada!")
    instancia = Database.forDataSource(cpds, None)
    instancia
  }
  def getInstance:Database= {
    logger.info(": - Nova Instancia do banco gerada")
    instancia match {
      case _ => conection()
    }
  }

  //fecha a conexão
  def closeDbConnection() {
    logger.info(": - Fechando Poll de conexão")
    cpds.close
  }

}