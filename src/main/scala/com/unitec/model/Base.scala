package com.unitec.model

import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

abstract class BaseEntity {
  val id:Long  
}

abstract class BaseTables[T <:BaseEntity](tag:Tag,nometabela:String) extends Table[T](tag,nometabela){
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
}
