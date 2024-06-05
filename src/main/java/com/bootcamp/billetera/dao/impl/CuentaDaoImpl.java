package com.bootcamp.billetera.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.billetera.dao.CuentaDao;
import com.bootcamp.billetera.mapper.CuentaRowMapper;
import com.bootcamp.billetera.model.Cuenta;

import lombok.extern.apachecommons.CommonsLog;

@Repository
@CommonsLog
public class CuentaDaoImpl implements CuentaDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public CuentaDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}

	@Override
	@Transactional
	public boolean crear(Cuenta cuenta) {
		try {
			
			String sql = "insert into cuenta(nro_cuenta, saldo,id_usuario) values(?,?,?)";
			jdbcTemplate.update(sql,cuenta.getNro_cuenta(),
									cuenta.getSaldo(),
									cuenta.getId_usuario());
			return true;
			
		}catch(Exception ex) {
			log.error("Error al crear:"+ex.getMessage(),ex);
			return false;
		}
	}
	
	@Override
	public boolean obtenerNumeroCuenta(int numeroCuenta) {
	    String sql = "SELECT COUNT(*) FROM cuenta WHERE nro_cuenta = ?";
	    int count = jdbcTemplate.queryForObject(sql, Integer.class, numeroCuenta);
	    return count > 0;
	}

	@Override
	public Cuenta obtenerCuentaPorUser(String username) {
		try {
			String sql = """
					select c.id_cuenta, c.nro_cuenta, c.saldo, c.id_usuario 
					from cuenta c inner join usuario u on c.id_usuario = u.id_usuario
					where u.username = ?
					""";
			Cuenta cuenta = jdbcTemplate.queryForObject(sql, 
										new CuentaRowMapper(), 
										new Object[] {username});
			
			return cuenta;
		}catch(Exception ex) {
			log.error("Error al obtener por usuario:"+ex.getMessage(),ex);
			return null;
		}

	}

	@Override
	public int obtenerSaldo(String username) {
		try {
			String sql="""
					select c.saldo
					from cuenta c inner join usuario u on c.id_usuario = u.id_usuario
					where u.username = ?
					""";
			return jdbcTemplate.queryForObject(sql,Integer.class, username );
			
		}catch(Exception ex) {
			log.error("Error al obtener por saldo:"+ex.getMessage(),ex);
			return -1;
		}	
		
	}

	@Override
	public void actualizarSaldo(String username, int nuevoSaldo) {
		try {
			String sql="""
					update cuenta c
					join usuario u ON c.id_usuario = u.id_usuario
					set c.saldo = ?
					where u.username = ?
					""";
			jdbcTemplate.update(sql,nuevoSaldo, username );
			
		}catch(Exception ex) {
			log.error("Error al actualizar saldo:"+ex.getMessage(),ex);
		}
	}

	@Override
	public Cuenta obtenerCuentaPorId(int id) {
		try {
			String sql ="""
					select id_cuenta, nro_cuenta, saldo, id_usuario 
					from cuenta 
					where id_cuenta = ?				
					""";
			Cuenta cuenta = jdbcTemplate.queryForObject(sql,
											new CuentaRowMapper(),
											new Object[] {id});
			
			return cuenta;
			
			
		}catch(Exception ex) {
			log.error("Error al actualizar saldo:"+ex.getMessage(),ex);
			return null;
		}

	}

	@Override
	public void actualizarSaldoPorId(int id, int nuevoSaldo) {
		try {
			String sql="""
					UPDATE cuenta 
					SET saldo = ?
					WHERE id_usuario = ?
					""";
			jdbcTemplate.update(sql,nuevoSaldo, id);
			
		}catch(Exception ex) {
			log.error("Error al actualizar saldo:"+ex.getMessage(),ex);
		}
		
	}

}
