package com.unitec.service
import com.unitec.dao.GenericDao

class GenericService[T,D](dao:D) {
  /*
  def get(): List[T] = {
    dao.findAll()
  }
  def getPorId(id: Long): Option[T] = {
    dao.findById(id)
  }
  def set(obj: T): Boolean = {
    dao.save(obj)
  }

  def delete(id: Long): Boolean = {
    dao.delete(id)
  }

  def update(obj : T): Boolean = {
    dao.update(obj)
  }
  */
}