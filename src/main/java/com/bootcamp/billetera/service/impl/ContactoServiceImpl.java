package com.bootcamp.billetera.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bootcamp.billetera.dao.ContactoDao;
import com.bootcamp.billetera.model.Contacto;
import com.bootcamp.billetera.service.ContactoService;

import lombok.extern.apachecommons.CommonsLog;

@Service
@CommonsLog
public class ContactoServiceImpl implements ContactoService{
	
	private final ContactoDao contactoDao;
	
	public ContactoServiceImpl(ContactoDao contactoDao) {
		this.contactoDao=contactoDao;
	}
	
	@Override
	public boolean crear(int idUsuario, int idContacto) {
		try {
			return contactoDao.crear(idUsuario,idContacto);
		}catch(Exception ex){
			log.error("Error al crear el usuario:"+ex.getMessage(),ex);
			return false;
		}
	}

	@Override
	public boolean eliminar(int idUsuario, int idContacto) {
		try {
			return contactoDao.eliminar(idUsuario,idContacto);
		}catch(Exception ex){
			log.error("Error al eliminar el usuario:"+ex.getMessage(),ex);
			return false;
		}
	}

	@Override
	public List<Contacto> obtenerContactos(int idUsuario) {
		return contactoDao.obtenerContactos(idUsuario);
	}

}
