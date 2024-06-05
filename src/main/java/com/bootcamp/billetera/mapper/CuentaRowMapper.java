package com.bootcamp.billetera.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bootcamp.billetera.model.Cuenta;

public class CuentaRowMapper implements RowMapper<Cuenta> {

	@Override
	public Cuenta mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cuenta cuenta = new Cuenta();
		cuenta.setId_cuenta(rs.getInt("id_cuenta"));
		cuenta.setNro_cuenta(rs.getInt("nro_cuenta"));
		cuenta.setSaldo(rs.getInt("saldo"));
		cuenta.setId_usuario(rs.getInt("id_usuario"));

		return cuenta;
	}

}
