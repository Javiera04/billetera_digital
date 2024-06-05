package com.bootcamp.billetera.dao;

import java.util.List;

import com.bootcamp.billetera.model.Transaccion;

public interface TransaccionDao {
	boolean crear(Transaccion transaccion);
	List<Transaccion> obtenerTransacciones(String username);
}
