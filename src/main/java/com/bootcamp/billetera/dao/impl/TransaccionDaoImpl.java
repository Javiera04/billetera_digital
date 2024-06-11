package com.bootcamp.billetera.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.billetera.dao.TransaccionDao;
import com.bootcamp.billetera.mapper.TransaccionRowMapper;
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

	@Override
	public List<Transaccion> obtenerTransacciones(String username) {
		try{
			String sql ="""
					select t.id_transaccion, t.id_cuenta_origen, t.id_cuenta_destino, 
					t.fecha_transaccion, t.monto, t.id_tipo
					from transaccion t inner join usuario u 
					on t.id_cuenta_origen = u.id_usuario or t.id_cuenta_destino = u.id_usuario
					where u.username = ?
					""";
			List<Transaccion> transacciones = jdbcTemplate.query(sql, 
					new TransaccionRowMapper(),
					new Object[] {username});
			
			return transacciones;
		}catch(Exception e) {
			System.out.println("Error:"+e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	@Transactional
	public boolean crearTransaccionDestino(Transaccion transaccion) {
		try {
			
			String sql = """
					insert into transaccion(id_cuenta_origen, id_cuenta_destino, fecha_transaccion, monto, id_tipo)
					values(?,?,?,?,?)
					""";
			jdbcTemplate.update(sql,
					transaccion.getId_cuenta_origen(),
					transaccion.getId_cuenta_destino(),
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
