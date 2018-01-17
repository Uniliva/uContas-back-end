package com.unitec.controller

import org.scalatra.ScalatraServlet
import com.unitec.dao.OrcamentoDao
import com.unitec.dao.OrcamentoDao
import com.unitec.model.OrcamentoEntity.Orcamento
import com.unitec.dao.CompraDao
import com.unitec.dao.LocalDao
import com.unitec.dao.MembroDao

class HomeController() extends ScalatraServlet {
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  get("/newtabelas"){
    CompraDao.createTable()
    LocalDao.createTable()
    MembroDao.createTable()
    OrcamentoDao.createTable()
  }
}