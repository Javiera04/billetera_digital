package com.bootcamp.billetera.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.billetera.dao.TransaccionDao;
import com.bootcamp.billetera.model.Transaccion;

import lombok.extern.apachecommons.CommonsLog;

@Repository
@CommonsLog
public class TransaccionDaoImpl implements TransaccionDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public TransaccionDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
	@Transactional
	public boolean crear(Transaccion transaccion) {
		try {
			
			String sql = """
					insert into transaccion(id_cuenta_origen, fecha_transaccion, monto, id_tipo)
					values(?,?,?,?)
					""";
			jdbcTemplate.update(sql,
					transaccion.getId_cuenta_origen(),
					transaccion.getFecha_transaccion(),
					transaccion.getMonto(),
					transaccion.getId_tipo());
			return true;
			
		}catch(Exception ex) {
			log.error("Error al crear:"+ex.getMessage(),ex);
			return false;
		}
	}

}
