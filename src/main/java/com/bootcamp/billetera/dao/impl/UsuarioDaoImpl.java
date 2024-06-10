package com.bootcamp.billetera.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.billetera.dao.CuentaDao;
import com.bootcamp.billetera.dao.UsuarioDao;
import com.bootcamp.billetera.mapper.UsuarioRowMapper;
import com.bootcamp.billetera.model.Cuenta;
import com.bootcamp.billetera.model.Usuario;

import lombok.extern.apachecommons.CommonsLog;

@Repository
@CommonsLog
public class UsuarioDaoImpl implements UsuarioDao, UserDetailsService{
	
	private JdbcTemplate jdbcTemplate;
	private CuentaDao cuentaDao;
	
	public UsuarioDaoImpl(JdbcTemplate jdbcTemplate,CuentaDao cuentaDao) {
		this.jdbcTemplate=jdbcTemplate;
		this.cuentaDao=cuentaDao;
	}
	
	@Override
	@Transactional
	public boolean crear(Usuario usuario) {
		try {
			String hashPass= new BCryptPasswordEncoder().encode(usuario.getPassword());
			
			String sql = "insert into usuario(username, password,nombre, apellido,email) values(?,?,?,?,?)";
			jdbcTemplate.update(sql,usuario.getUsername(),
									hashPass,
									usuario.getNombre(),
									usuario.getApellido(),
									usuario.getEmail());
			
			int idUsuario = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
	        
			Cuenta cuenta = new Cuenta();
			
	        cuenta.setNro_cuenta(generateRandomAccountNumber());
            cuenta.setSaldo(0);
            cuenta.setId_usuario(idUsuario); 
	        
			boolean cuentaCreada= cuentaDao.crear(cuenta);
            
			return cuentaCreada;
			
		}catch(Exception ex) {
			log.error("Error al crear:"+ex.getMessage(),ex);
			return false;
		}
	}

	private int generateRandomAccountNumber() {
		int max = 100000;
	    int min = 1;
	    int range = max - min + 1;
	    int randomAccountNumber;
	    boolean exists;
	    
	    // Bucle para generar un número único de cuenta
	    do {
	        randomAccountNumber = (int)(Math.random() * range) + min;
	        exists = cuentaDao.obtenerNumeroCuenta(randomAccountNumber);
	    } while (exists); // Continuar generando hasta que se encuentre un número único
	    
	    return randomAccountNumber;
	}
	
	@Override
	public Usuario obtenerPorId(int id) {
		try{
			String sql="select id_usuario, username, password, nombre, apellido, email from usuario where id_usuario=?";
			Usuario usuario =jdbcTemplate.queryForObject(sql, 
										  	new UsuarioRowMapper(), 
										  	new Object [] {id});
			
			return usuario;
		}catch(Exception ex) {
			log.error("Error al obtener:"+ex.getMessage(),ex);
			return null;
		}

	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = obtenerPorUser(username);
		
		if(usuario != null) {
			List<GrantedAuthority> permissions= new ArrayList<>();
			
			GrantedAuthority ga= new SimpleGrantedAuthority("ROLE_ADMIN");
			permissions.add(ga);
			
			return new User(usuario.getUsername(),usuario.getPassword(),permissions);
		}
		
		return null;
	}

	@Override
	public Usuario obtenerPorUser(String username) {
		try{
			String sql="select id_usuario, username, password, nombre, apellido, email from usuario where username=?";
			Usuario usuario =jdbcTemplate.queryForObject(sql, 
										  	new UsuarioRowMapper(), 
										  	new Object [] {username});
			
			return usuario;
		}catch(Exception ex) {
			log.error("Error al obtener por usuario:"+ex.getMessage(),ex);
			return null;
		}
	}

	@Override
	public Usuario obtenerPorNroCuenta(int nroCuenta) {
		try{
			String sql="""
					select u.id_usuario, u.username, u.password, u.nombre, u.apellido, u.email 
					from usuario u inner join cuenta c on u.id_usuario = c.id_usuario
					where c.nro_cuenta = ?
					""";
			Usuario usuario =jdbcTemplate.queryForObject(sql, 
										  	new UsuarioRowMapper(), 
										  	new Object [] {nroCuenta});
			
			return usuario;
		}catch(Exception ex) {
			log.error("Error al obtener por usuario:"+ex.getMessage(),ex);
			return null;
		}
	}

}
