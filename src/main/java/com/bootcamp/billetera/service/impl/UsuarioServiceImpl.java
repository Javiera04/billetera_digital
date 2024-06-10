package com.bootcamp.billetera.service.impl;

import org.springframework.stereotype.Service;

import com.bootcamp.billetera.dao.UsuarioDao;
import com.bootcamp.billetera.model.Usuario;
import com.bootcamp.billetera.service.CuentaService;
import com.bootcamp.billetera.service.UsuarioService;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class UsuarioServiceImpl implements UsuarioService{
		
	private final UsuarioDao usuarioDao;
	
	public UsuarioServiceImpl(CuentaService cuentaService,UsuarioDao usuarioDao) {
		this.usuarioDao=usuarioDao;
	}
	
	@Override
	public boolean crear(Usuario usuario) {
		try {
			return usuarioDao.crear(usuario);
		}catch(Exception ex){
			log.error("Error al crear usuario:"+ex.getMessage(),ex);
			return false;
		}
	}

	@Override
	public Usuario obtenerPorUser(String username) {
		return usuarioDao.obtenerPorUser(username);
	}

	@Override
	public Usuario obtenerPorId(int id) {
		return usuarioDao.obtenerPorId(id);
	}

	@Override
	public Usuario obtenerPorNroCuenta(int nroCuenta) {
		return usuarioDao.obtenerPorNroCuenta(nroCuenta);
	}

	

}
