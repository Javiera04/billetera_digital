package com.bootcamp.billetera.service.impl;

import org.springframework.stereotype.Service;

import com.bootcamp.billetera.dao.TransaccionDao;
import com.bootcamp.billetera.model.Transaccion;
import com.bootcamp.billetera.service.TransaccionService;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class TransaccionServiceImpl implements TransaccionService{
	
	private final TransaccionDao transaccionDao;
	
	public TransaccionServiceImpl(TransaccionDao transaccionDao) {
		this.transaccionDao=transaccionDao;
	}
	
	@Override
	public boolean crear(Transaccion transaccion) {
		try {
			return transaccionDao.crear(transaccion);
		}catch(Exception ex){
			log.error("Error al crear la transaccion:"+ex.getMessage(),ex);
			return false;
		}
	}

}
