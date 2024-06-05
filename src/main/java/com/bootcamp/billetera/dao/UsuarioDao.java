package com.bootcamp.billetera.dao;

import com.bootcamp.billetera.model.Usuario;

public interface UsuarioDao {
	boolean crear(Usuario usuario);
	Usuario obtenerPorId(int id);
	Usuario obtenerPorUser(String username);
}
