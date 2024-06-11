package com.bootcamp.billetera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.billetera.model.Usuario;
import com.bootcamp.billetera.service.UsuarioService;

@Controller
@RequestMapping("/registro")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService=usuarioService;
	}
	
	@GetMapping
	public ModelAndView formGet() {
		
		return new ModelAndView("registro.jsp");
	}
	
	@PostMapping
	public ModelAndView formPost(@ModelAttribute Usuario usuario) {
		ModelAndView mav = new ModelAndView("registro.jsp");
		boolean resultado= usuarioService.crear(usuario);
		
		if(resultado) {
			mav.addObject("message", "Usuario creado exitosamente");
			mav.addObject("alertClass", "alert-success");
		}else {
			mav.addObject("message", "Error al crear el usuario");
			mav.addObject("alertClass", "alert-danger");
		}
		
		return mav;
	}

}
