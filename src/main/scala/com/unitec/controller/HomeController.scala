package com.unitec.controller

import org.scalatra.ScalatraServlet
import com.unitec.dao.OrcamentoDao
import com.unitec.dao.OrcamentoDao
import com.unitec.model.OrcamentoEntity.Orcamento

class HomeController() extends ScalatraServlet {
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  get("/login") {
    views.html.vlogin.login("Teste");
  }


  get("/dash/home") {
    views.html.dashboard.dash()
  }
  post("/dash/home") {
    views.html.dashboard.dash()
  }
}