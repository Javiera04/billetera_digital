package com.bootcamp.billetera.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {
	
	private Usuario usuario;

	@BeforeEach
	void setUp() throws Exception {
		usuario = new Usuario();
	}

	@Test
	void testGetAndSetId_usuario() {
		usuario.setId_usuario(1);
		assertEquals(1,usuario.getId_usuario());
	}
	
	@Test
	void testGetAndSetUsername() {
		usuario.setUsername("Usuario 1");
		assertEquals("Usuario 1",usuario.getUsername());
	}
	
	@Test
	void testGetAndSetPassword() {
		usuario.setPassword("12345");
		assertEquals("12345",usuario.getPassword());
	}
	
	@Test
	void testGetAndSetNombre() {
		usuario.setNombre("Usuario");
		assertEquals("Usuario",usuario.getNombre());
	}
	
	@Test
	void testGetAndSetApellido() {
		usuario.setApellido("Real");
		assertEquals("Real",usuario.getApellido());
	}
	
	@Test
	void testGetAndSetEmail() {
		usuario.setEmail("usuario@real.cl");
		assertEquals("usuario@real.cl",usuario.getEmail());
	}

}
