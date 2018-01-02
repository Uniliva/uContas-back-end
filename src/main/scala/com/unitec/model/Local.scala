package com.unitec.model

import slick.jdbc.MySQLProfile.api.anyToShapedValue
import slick.jdbc.MySQLProfile.api.longColumnType
import slick.jdbc.MySQLProfile.api.stringColumnType
import slick.lifted.ProvenShape.proveShapeOf
import slick.lifted.Tag

object Local {
  
  case class Local(id: Long, var descricao: String, var endereco: String) extends BaseEntity
  
  
  class Locais(tag: Tag) extends BaseTables[Local](tag, "LOCAIS") {
    def descricao = column[String]("DESCRICAO")
    def endereco = column[String]("ENDERECO")
    def * = (id, descricao, endereco) <> (Local.tupled, Local.unapply)
  }

}