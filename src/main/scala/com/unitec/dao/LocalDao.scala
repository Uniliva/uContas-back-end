package com.unitec.dao

import com.unitec.model.Local.Locais
import com.unitec.model.Local.Local

import slick.jdbc.MySQLProfile.api.TableQuery

object LocalDao extends GenericDao[Local,Locais](TableQuery[Locais]) {

}