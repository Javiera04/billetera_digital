package com.bootcamp.billetera.model;

import lombok.Data;

/**
 * La clase 'Transaccion' representa la entidad tipo_transaccion de la base de datos de la billetera virtual.
 * Almacena información de los tipo de transacciones.
 */
@Data
public class TipoTransaccion {
	/**Identificador del tipo de transaccion*/
	private int id_tipo;
	/**Identificador del nombre de la transaccion*/
	private String nombre_tipo;
}
