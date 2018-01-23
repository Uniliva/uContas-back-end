package com.unitec.dao

import com.unitec.model.Membro.Membro
import com.unitec.model.Membro.Membros

import slick.jdbc.MySQLProfile.api.TableQuery

object MembroDao extends GenericDao[Membro,Membros](TableQuery[Membros]) {

}