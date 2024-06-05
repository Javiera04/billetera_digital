package com.bootcamp.billetera.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bootcamp.billetera.model.Contacto;

public class ContactoRowMapper implements RowMapper<Contacto>{

	@Override
	public Contacto mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contacto contacto = new Contacto();
		contacto.setId_usuario(rs.getInt("id_usuario"));
		contacto.setId_contacto(rs.getInt("id_contacto"));
		contacto.setNombre(rs.getString("nombre"));
		contacto.setApellido(rs.getString("apellido"));
		contacto.setEmail(rs.getString("email"));
		contacto.setNro_cuenta(rs.getInt("nro_cuenta"));
		return contacto;
	}

}
