package br.com.devQueijo.model

import com.unitec.model.BaseTables

import slick.jdbc.MySQLProfile.api.anyToShapedValue
import slick.jdbc.MySQLProfile.api.longColumnType
import slick.jdbc.MySQLProfile.api.stringColumnType
import slick.lifted.ProvenShape.proveShapeOf
import slick.lifted.Tag
import com.unitec.model.BaseEntity

object Membro {
  case class Membro(id: Long , var nome: String, var cpf: String,var sexo: String) extends BaseEntity
  //mapeamento
  class Membros(tag: Tag) extends BaseTables[Membro](tag, "MEMBROS") {
    def nome = column[String]("NOME")
    def cpf = column[String]("CPF")
    def sexo = column[String]("SEXO")
    def * = (id, nome, cpf, sexo) <> (Membro.tupled, Membro.unapply)
  }

}