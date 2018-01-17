package br.com.devQueijo.model
import com.unitec.model.BaseEntity
import com.unitec.model.BaseTables
import com.unitec.model.Local.Locais
import com.unitec.model.OrcamentoEntity.Orcamentos

import br.com.devQueijo.model.Membro.Membros
//import slick.jdbc.H2Profile.api._
import slick.jdbc.MySQLProfile.api._

object Compra {
  
case class Compra(id:Long,var descricao: String,var valor: Double,var localID: Long,var membroID:Long,var orcamento:Long) extends BaseEntity

class Compras (tag:Tag) extends BaseTables[Compra](tag,"COMPRAS"){
      def descricao = column[String]("DESCRICAO")
      def valor = column[Double]("VALOR")
      def localFK = column[Long]("LOCAL_ID")
      def membroFK = column[Long]("MEMBRO_ID")
      def OrcamentoFK = column[Long]("ORCAMENTO_ID")
      
      //foreign Key
      def fk1 = foreignKey("LOCAL_FK", localFK, TableQuery[Locais])(_.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
      def fk2 = foreignKey("MEMBRO_FK", localFK, TableQuery[Membros])(_.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
      def fk3 = foreignKey("ORCAMENTO_FK", localFK, TableQuery[Orcamentos])(_.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
      
      def * =(id,descricao,valor,localFK,membroFK,OrcamentoFK) <> (Compra.tupled,Compra.unapply)
  }


}