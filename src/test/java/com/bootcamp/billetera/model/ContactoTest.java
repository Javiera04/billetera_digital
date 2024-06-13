package com.bootcamp.billetera.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactoTest {
	
	private Contacto contacto;

	@BeforeEach
	void setUp() throws Exception {
		contacto = new Contacto();
	}

	@Test
	void testGetAndSetId_usuario() {
		contacto.setId_usuario(1);
		assertEquals(1,contacto.getId_usuario());
	}
	
	@Test
	void testGetAndSetId_contacto() {
		contacto.setId_contacto(2);
		assertEquals(2,contacto.getId_contacto());
	}
	
	@Test
	void testGetAndSetNombre() {
		contacto.setNombre("Usuario");
		assertEquals("Usuario",contacto.getNombre());
	}
	
	@Test
	void testGetAndSetApellido() {
		contacto.setApellido("Real");
		assertEquals("Real",contacto.getApellido());
	}
	
	@Test
	void testGetAndSetEmail() {
		contacto.setEmail("usuario@real.cl");
		assertEquals("usuario@real.cl",contacto.getEmail());
	}
	
	@Test
	void testGetAndSetNro_cuenta() {
		contacto.setNro_cuenta(13548);
		assertEquals(13548,contacto.getNro_cuenta());
	}
	
	
}
