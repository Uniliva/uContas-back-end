package com.unitec.service
import com.unitec.dao.GenericDao
import com.unitec.dao.GenericDao
import com.unitec.model.BaseTables
import com.unitec.dao.GenericDao
import com.unitec.model.BaseEntity

class Services[T <: BaseEntity,D <: BaseTables[T]](dao: GenericDao[T,D]) {
 
  def getAll(): List[T] = {
    dao.findAll()
  }
  def getPorId(id: Long): Option[T] = {
    dao.findById(id)
  }
  def save(obj: T): Boolean = {
    dao.save(obj)
  }

  def delete(id: Long): Boolean = {
    dao.delete(id)
  }

  def update(obj : T): Boolean = {
    dao.update(obj)
  }
  
}