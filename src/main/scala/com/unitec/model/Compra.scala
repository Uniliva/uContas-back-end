package com.unitec.model

import com.unitec.model.Local.Locais
import com.unitec.model.Membro.Membros
import com.unitec.model.OrcamentoEntity.Orcamentos

import slick.jdbc.MySQLProfile.api._
import java.sql.Date
import java.sql.Timestamp


object Compra {

  case class Compra(id: Long = 0, var descricao: String, var data: Timestamp, var valor: Double, var localID: Long, var membroID: Long, var orcamento: Long) extends BaseEntity

  class Compras(tag: Tag) extends BaseTables[Compra](tag, "COMPRAS") {
    def descricao = column[String]("DESCRICAO")
    def data = column[Timestamp ]("Date")
    def valor = column[Double]("VALOR")
    def localID = column[Long]("LOCAL_ID")
    def membroID = column[Long]("MEMBRO_ID")
    def OrcamentoID = column[Long]("ORCAMENTO_ID")

    //foreign Key
    def localFK = foreignKey("LOCAL_FK_2", localID, TableQuery[Locais])(_.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
    def membroFK = foreignKey("MEMBRO_FK_2", membroID, TableQuery[Membros])(_.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)
    def orcamentoFK = foreignKey("ORCAMENTO_FK_2", OrcamentoID, TableQuery[Orcamentos])(_.id, onUpdate = ForeignKeyAction.NoAction, onDelete = ForeignKeyAction.NoAction)

    def * = (id, descricao,data, valor, localID, membroID, OrcamentoID) <> (Compra.tupled, Compra.unapply)
  }

}