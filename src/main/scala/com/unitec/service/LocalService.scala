package com.unitec.service
import com.unitec.model.Local.Local
import com.unitec.dao.LocalDao
import com.unitec.model.Local.Locais


object LocalService extends Services[Local,Locais](LocalDao) {
  

}