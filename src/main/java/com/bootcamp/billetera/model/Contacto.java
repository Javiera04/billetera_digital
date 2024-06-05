package com.bootcamp.billetera.model;

import lombok.Data;

@Data
public class Contacto {
	private int id_usuario;
	private int id_contacto;
	private String nombre;
	private String apellido;
	private String email;
	private int nro_cuenta;
}
