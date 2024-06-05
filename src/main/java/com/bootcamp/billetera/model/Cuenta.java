package com.bootcamp.billetera.model;

import lombok.Data;

/**
 * La clase 'Cuenta' representa la entidad cuenta de la base de datos de la billetera virtual.
 * Almacena información de la cuenta, como identificador de la cuenta, numero de cuenta,
 * saldo y la relacion con la tabla cliente.
 */
@Data
public class Cuenta {
	/**Identificador de la cuenta*/
	private int id_cuenta;
	/**Numero de la cuenta*/
	private int nro_cuenta;
	/**Saldo de la cuenta*/
	private int saldo;
	/**Identificador del usuario (fk)*/
	private int id_usuario;
		
	
}
