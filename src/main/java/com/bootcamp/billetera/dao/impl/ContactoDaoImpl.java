package com.bootcamp.billetera.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.billetera.dao.ContactoDao;
import com.bootcamp.billetera.model.Contacto;

import lombok.extern.apachecommons.CommonsLog;

import com.bootcamp.billetera.mapper.*;

@Repository
@CommonsLog
public class ContactoDaoImpl implements ContactoDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public ContactoDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
	@Transactional
	public boolean crear(int idUsuario, int idContacto) {
		try {
			
			String sql = """
					insert into contacto(id_usuario, id_contacto)
					values(?,?)
					""";
			jdbcTemplate.update(sql,idUsuario,idContacto);
			return true;
			
		}catch(Exception ex) {
			log.error("Error al crear contacto:"+ex.getMessage(),ex);
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean eliminar(int idUsuario, int idContacto) {
		try {
			
			String sql = """
					delete from contacto
					where id_usuario = ? and id_contacto = ?
					""";
			jdbcTemplate.update(sql,idUsuario,idContacto);
			return true;
			
		}catch(Exception ex) {
			log.error("Error al eliminar contacto:"+ex.getMessage(),ex);
			return false;
		}
		
	}

	@Override
	public List<Contacto> obtenerContactos(int idUsuario) {
		try {
			String sql = """
					select c.id_usuario, c.id_contacto, u.nombre, u.apellido, u.email, cu.nro_cuenta
					from usuario u  inner join contacto c  on u.id_usuario = c.id_contacto
					inner join cuenta cu on u.id_usuario = cu.id_usuario
					where c.id_usuario = ?
					""";
			List<Contacto> contactos = jdbcTemplate.query(sql,
					new ContactoRowMapper(),
					new Object[] {idUsuario});
			
			return contactos;
			
		}catch(Exception e) {
			System.out.println("Error:"+e.getMessage());
			return Collections.emptyList();
		}

	}

}
