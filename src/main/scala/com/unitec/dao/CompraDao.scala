package com.unitec.dao

import com.unitec.model.Compra.Compra
import com.unitec.model.Compra.Compras

import slick.jdbc.MySQLProfile.api.TableQuery

object CompraDao extends GenericDao[Compra,Compras](TableQuery[Compras]) {
 
}