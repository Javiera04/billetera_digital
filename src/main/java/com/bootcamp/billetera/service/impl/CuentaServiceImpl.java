package com.bootcamp.billetera.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.billetera.dao.CuentaDao;
import com.bootcamp.billetera.model.Cuenta;
import com.bootcamp.billetera.service.CuentaService;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class CuentaServiceImpl implements CuentaService{

	private final CuentaDao cuentaDao;
	
	public CuentaServiceImpl(CuentaDao cuentaDao) {
		this.cuentaDao=cuentaDao;
	}
	
	@Override
	@Transactional
	public int crear(Cuenta cuenta) {
		return -1;
		
	}

	@Override
	public Cuenta obtenerPorUser(String username) {		
		return cuentaDao.obtenerCuentaPorUser(username);
	}

	@Override
	public boolean retirar(String username, int monto) {
		try {
			int saldoActual = cuentaDao.obtenerSaldo(username);
			if(saldoActual < monto) {
				System.out.println("Saldo insuficiente");
				return false;
			}
			else if(monto<=0){
				System.out.println("Monto a retirar menor a 0");
				return false;
			}
			else {
				cuentaDao.actualizarSaldo(username, saldoActual-monto);
				return true;
			}
			
		}catch(Exception ex) {
			log.error("Error al retirar saldo:"+ex.getMessage(),ex);
			return false;
		}
		
	}

	@Override
	public boolean depositar(String username, int monto) {
		try {
			int saldoActual = cuentaDao.obtenerSaldo(username);
			if(monto<=0) {
				System.out.println("Monto a depositar menor o igual a 0");
				return false;
			}
			else {
				cuentaDao.actualizarSaldo(username, saldoActual+monto);
				return true;
			}
			
		}catch(Exception ex) {
			log.error("Error al retirar saldo:"+ex.getMessage(),ex);
			return false;
		}
		
	}

	@Override
	public Cuenta obtenerPorId(int id) {
		return cuentaDao.obtenerCuentaPorId(id);
	}

	@Override
	public boolean transferir(int idOrigen, int idDestino, int monto) {
		Cuenta cuentaOrigen = cuentaDao.obtenerCuentaPorId(idOrigen);
		Cuenta cuentaDestino = cuentaDao.obtenerCuentaPorId(idDestino);
		
		if(cuentaOrigen.getSaldo()>=monto && idOrigen != idDestino && monto>0) {
			cuentaDao.actualizarSaldoPorId(idOrigen, cuentaOrigen.getSaldo()-monto);
			cuentaDao.actualizarSaldoPorId(idDestino, cuentaDestino.getSaldo()+monto);
			return true;
		}else {
			System.out.println("Saldo insuficiente en la cuenta de origen");
			return false;
		}
		
	}


	

}
