package com.bootcamp.billetera.model;

import lombok.Data;

/**
 * La clase 'Usuario' representa la entidad usuario de la base de datos de la billetera virtual.
 * Almacena información personal del Usuario, como nombre, rut, nombre y apellido.
 * 
 */
@Data
public class Usuario {
	/**Identificador del Usuario*/
	private int id_usuario;
	/**username del Usuario*/
	private String username;
	/**contraseña del Usuario*/
	private String password;
	/**Nombre del Usuario*/
	private String nombre;
	/**Apellido del Usuario*/
	private String apellido;
	/**Apellido del Usuario*/
	private String email;
	
	
	
}
