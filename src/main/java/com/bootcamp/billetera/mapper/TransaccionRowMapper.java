package com.bootcamp.billetera.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bootcamp.billetera.model.Transaccion;

public class TransaccionRowMapper implements RowMapper<Transaccion>{

	@Override
	public Transaccion mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transaccion transaccion = new Transaccion();
		transaccion.setId_transaccion(rs.getInt("id_transaccion"));
		transaccion.setId_cuenta_origen(rs.getInt("id_cuenta_origen"));
		transaccion.setId_cuenta_destino(rs.getInt("id_cuenta_destino"));
		transaccion.setFecha_transaccion(rs.getDate("fecha_transaccion"));
		transaccion.setMonto(rs.getInt("monto"));
		transaccion.setId_tipo(rs.getInt("id_tipo"));
		
		return transaccion;
	}

}
