package com.bootcamp.billetera.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TipoTransaccionTest {

	private TipoTransaccion tipoTransaccion;
	
	@BeforeEach
	void setUp() throws Exception {
		tipoTransaccion = new TipoTransaccion();
		
	}

	@Test
	void testGetAndSetId_tipo() {
		tipoTransaccion.setId_tipo(1);
		assertEquals(1,tipoTransaccion.getId_tipo());
	}
	
	@Test
	void testGetAndSetNombre_tipo() {
		tipoTransaccion.setNombre_tipo("Deposito");
		assertEquals("Deposito",tipoTransaccion.getNombre_tipo());
	}

}
