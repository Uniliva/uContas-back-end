package com.unitec.dao

import com.unitec.util.BaseUtil

import slick.jdbc.MySQLProfile.api._
import com.unitec.model.BaseEntity
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.unitec.model.BaseTables

abstract class GenericDao[T <: BaseEntity]() {
  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global

  //conexão banco de dados
  def db: Database = BaseUtil.getInstance

  implicit val tb: TableQuery[_]

  def findAll(): List[T] = {
    val action = tb.result
    val resp = db.run(action)
    //obriga a future a executar
    Await.result(resp, Duration.Inf).toList.asInstanceOf[List[T]]
  }
  
  def save (obj:T):Unit

  def findById(idx: Long): T

}