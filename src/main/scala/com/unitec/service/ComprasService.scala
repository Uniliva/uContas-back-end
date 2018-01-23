package com.unitec.service

import org.slf4j.LoggerFactory
import com.unitec.model.Compra.Compra
import com.unitec.dao.CompraDao
import com.unitec.model.Compra
import com.unitec.model.Compra.Compras

object ComprasService extends Services[Compra,Compras](CompraDao) {

}