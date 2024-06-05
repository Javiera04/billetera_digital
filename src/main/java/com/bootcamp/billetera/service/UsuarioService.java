package com.bootcamp.billetera.service;

import com.bootcamp.billetera.model.Usuario;

public interface UsuarioService {
	
	boolean crear(Usuario usuario);
	Usuario obtenerPorUser(String username);

}
