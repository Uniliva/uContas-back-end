package com.unitec.model


import slick.jdbc.MySQLProfile.api.anyToShapedValue
import slick.jdbc.MySQLProfile.api.longColumnType
import slick.jdbc.MySQLProfile.api.stringColumnType
import slick.lifted.ProvenShape.proveShapeOf
import slick.lifted.Tag

object Membro {
  case class Membro(id: Long , var nome: String, var cpf: String,var sexo: String,var email:String,var senha:String) extends BaseEntity
  //mapeamento
  class Membros(tag: Tag) extends BaseTables[Membro](tag, "MEMBROS") {
    def nome = column[String]("NOME")
    def cpf = column[String]("CPF")
    def sexo = column[String]("SEXO")
     def email = column[String]("EMAIL")
     def senha = column[String]("SENHA")
    def * = (id, nome, cpf, sexo,email,senha) <> (Membro.tupled, Membro.unapply)
  }

}