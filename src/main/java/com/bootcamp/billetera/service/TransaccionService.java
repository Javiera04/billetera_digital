package com.bootcamp.billetera.service;

import java.util.List;

import com.bootcamp.billetera.model.Transaccion;

public interface TransaccionService {
	boolean crear(Transaccion transaccion);
	List<Transaccion> obtenerTransacciones(String username);
}
