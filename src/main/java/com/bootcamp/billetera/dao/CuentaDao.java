package com.bootcamp.billetera.dao;

import com.bootcamp.billetera.model.Cuenta;

public interface CuentaDao {
	boolean crear(Cuenta cuenta);
	boolean obtenerNumeroCuenta(int numeroCuenta);
	Cuenta obtenerPorUser(String username);
	int obtenerSaldo(String username);
	void actualizarSaldo(String username, int nuevoSaldo);

}
