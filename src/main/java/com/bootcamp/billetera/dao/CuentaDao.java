package com.bootcamp.billetera.dao;

import com.bootcamp.billetera.model.Cuenta;

public interface CuentaDao {
	boolean crear(Cuenta cuenta);
	boolean obtenerNumeroCuenta(int numeroCuenta);
	Cuenta obtenerCuentaPorUser(String username);
	int obtenerSaldo(String username);
	void actualizarSaldo(String username, int nuevoSaldo);
	Cuenta obtenerCuentaPorId(int id);
	void actualizarSaldoPorId(int id, int nuevoSaldo);

}
