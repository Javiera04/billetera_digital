package com.bootcamp.billetera.dao;

import java.util.List;

import com.bootcamp.billetera.model.Contacto;

public interface ContactoDao {
	boolean crear(int idUsuario, int idContacto);
	boolean eliminar(int idUsuario, int idContacto);
	List<Contacto> obtenerContactos(int idUsuario);
}
