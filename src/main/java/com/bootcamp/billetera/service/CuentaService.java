package com.bootcamp.billetera.service;

import com.bootcamp.billetera.model.Cuenta;

public interface CuentaService {

	int crear(Cuenta cuenta);
	Cuenta obtenerPorUser(String username);
	boolean retirar(String username, int monto);
	boolean depositar(String username, int monto);
	Cuenta obtenerPorId(int id);
	boolean transferir(int idOrigen, int idDestino, int monto);

}
