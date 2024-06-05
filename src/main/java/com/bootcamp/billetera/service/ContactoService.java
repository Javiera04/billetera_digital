package com.bootcamp.billetera.service;

import java.util.List;

import com.bootcamp.billetera.model.Contacto;

public interface ContactoService {
	boolean crear(int idUsuario, int idContacto);
	boolean eliminar(int idUsuario, int idContacto);
	List<Contacto> obtenerContactos(int idUsuario);

}
