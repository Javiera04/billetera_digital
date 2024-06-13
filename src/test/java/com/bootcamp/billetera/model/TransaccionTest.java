package com.bootcamp.billetera.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransaccionTest {

	private Transaccion transaccion;
	
	@BeforeEach
	void setUp() throws Exception {
		transaccion = new Transaccion();
	}

	@Test
	void testGetAndSetId_transaccion() {
		transaccion.setId_transaccion(1);
		assertEquals(1,transaccion.getId_transaccion());
	}
	
	@Test
	void testGetAndSetId_cuenta_origen() {
		transaccion.setId_cuenta_origen(2);
		assertEquals(2,transaccion.getId_cuenta_origen());
	}
	
	@Test
	void testGetAndSetId_cuenta_destino() {
		transaccion.setId_cuenta_destino(3);
		assertEquals(3,transaccion.getId_cuenta_destino());
	}
	
	@Test
	void testGetAndSetFecha_transaccion() {
		transaccion.setFecha_transaccion(new Date());
		assertEquals(new Date(),transaccion.getFecha_transaccion());
	}
	
	@Test
	void testGetAndSetMonto() {
		transaccion.setMonto(2000);
		assertEquals(2000,transaccion.getMonto());
	}
	
	@Test
	void testGetAndSetId_tipo() {
		transaccion.setId_tipo(3);
		assertEquals(3,transaccion.getId_tipo());
	}
	
	

}
