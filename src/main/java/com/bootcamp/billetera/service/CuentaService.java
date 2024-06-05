package com.bootcamp.billetera.service;

import com.bootcamp.billetera.model.Cuenta;

public interface CuentaService {

	int crear(Cuenta cuenta);
	Cuenta obtenerPorUser(String username);
	void retirar(String username, int monto);
	void depositar(String username, int monto);

}
