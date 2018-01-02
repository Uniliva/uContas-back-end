package com.unitec.controller

import org.scalatra.ScalatraServlet
import com.unitec.dao.OrcamentoDao
import com.unitec.dao.OrcamentoDao
import com.unitec.model.OrcamentoEntity.Orcamento

class HomeController() extends ScalatraServlet {
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  get("/teste") {
    val x = OrcamentoDao.findAll()
    OrcamentoDao.save(Orcamento(0, "uai 2", 1300))
    var t = OrcamentoDao.findById(1)
    t.desc = "Nova descricao 22"
    t.valor = 3000
    
    OrcamentoDao.update(t)

    println("Depois \n" + t)
  }

}