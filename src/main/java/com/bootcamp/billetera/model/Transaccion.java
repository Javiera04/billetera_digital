package com.bootcamp.billetera.model;

import java.util.Date;

import lombok.Data;

/**
 * La clase 'Transaccion' representa la entidad transaccion de la base de datos de la billetera virtual.
 * Almacena información de las transacciones.
 */
@Data
public class Transaccion {
	/**Identificador de la transaccion*/
	private int id_transaccion;
	/**Identificador de la cuenta de origen de la transaccion(fk)*/
	private int id_cuenta_origen;
	/**Identificador de la cuenta de destino de la transaccion(fk)*/
	private int id_cuenta_destino;
	/**Fecha en la que se realizo la transaccion*/
	private Date fecha_transaccion;
	/**Monto de la transaccion*/
	private int monto;
	/**Identificador del tipo de transaccion(fk)*/
	private int id_tipo;	
	
	
}
