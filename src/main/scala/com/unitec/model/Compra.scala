package br.com.devQueijo.model
import com.unitec.model.BaseEntity
import com.unitec.model.BaseTables
import com.unitec.model.Local.Locais
import com.unitec.model.OrcamentoEntity.Orcamentos

import br.com.devQueijo.model.Membro.Membros
import slick.jdbc.MySQLProfile.api._
import br.com.devQueijo.model.Membro.Membros
import br.com.devQueijo.model.Membro.Membros
import com.unitec.model.OrcamentoEntity.Orcamentos

object Compra {
  
case class Compra(id:Long,var descricao: String,var valor: Double,var localID: Long,var membroID:Long,var orcamento:Long) extends BaseEntity

class Compras (tag:Tag) extends BaseTables[Compra](tag,"COMPRAS"){
      def descricao = column[String]("DESCRICAO")
      def valor = column[Double]("VALOR")
      def localID = column[Long]("LOCAL_ID")
      def membroID = column[Long]("MEMBRO_ID")
      def OrcamentoID = column[Long]("ORCAMENTO_ID")
      
      //foreign Key
      def localFK = foreignKey("LOCAL_FK_2", localID, TableQuery[Locais])(_.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
      def membroFK = foreignKey("MEMBRO_FK_2", membroID, TableQuery[Membros])(_.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
      def orcamentoFK = foreignKey("ORCAMENTO_FK_2", OrcamentoID, TableQuery[Orcamentos])(_.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
      
      def * =(id,descricao,valor,localID,membroID,OrcamentoID) <> (Compra.tupled,Compra.unapply)
  }


}