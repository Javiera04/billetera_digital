package com.bootcamp.billetera.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CuentaTest {
	
	private Cuenta cuenta;

	@BeforeEach
	void setUp() throws Exception {
		cuenta = new Cuenta();
	}

	@Test
	void testGetAndSetId_cuenta() {
		cuenta.setId_cuenta(1);
		assertEquals(1,cuenta.getId_cuenta());
	}
	
	@Test
	void testGetAndSetNro_cuenta() {
		cuenta.setNro_cuenta(13548);
		assertEquals(13548,cuenta.getNro_cuenta());
	}
	
	@Test
	void testGetAndSetSaldo() {
		cuenta.setSaldo(150000);
		assertEquals(150000,cuenta.getSaldo());
	}
	
	@Test
	void testGetAndSetId_usuario() {
		cuenta.setId_usuario(10);
		assertEquals(10,cuenta.getId_usuario());
	}
	
	
}
