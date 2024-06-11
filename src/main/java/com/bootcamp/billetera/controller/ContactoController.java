package com.bootcamp.billetera.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.billetera.model.Contacto;
import com.bootcamp.billetera.model.Usuario;
import com.bootcamp.billetera.service.ContactoService;
import com.bootcamp.billetera.service.UsuarioService;


@Controller
@RequestMapping("/contactos")
public class ContactoController {
	
	private final ContactoService contactoService;
	private final UsuarioService usuarioService;
	
	public ContactoController(ContactoService contactoService,UsuarioService usuarioService) {
		this.contactoService=contactoService;
		this.usuarioService=usuarioService;
	}
	
	@GetMapping
	public ModelAndView obtenerContactos(Authentication authentication) {
		ModelAndView mav = new ModelAndView("contacto.jsp");
		String cuentaAuth = authentication.getName();
		Usuario usuario = usuarioService.obtenerPorUser(cuentaAuth);
		
	
		List<Contacto> contacto = contactoService.obtenerContactos(usuario.getId_usuario());
	
		mav.addObject("contactos",contacto);
		
		return mav;
	}
	
	@PostMapping("/agregar")
	public ModelAndView formAgregar(@RequestParam int nro_cuenta,
			Authentication authentication) {
		 ModelAndView mav = new ModelAndView("contacto.jsp");
		 String cuentaAuth = authentication.getName();
		 Usuario usuario = usuarioService.obtenerPorUser(cuentaAuth);
		 Usuario contacto = usuarioService.obtenerPorNroCuenta(nro_cuenta);
		    
		 if (contacto != null) {
			 boolean resultado = contactoService.crear(usuario.getId_usuario(), contacto.getId_usuario());

			 if (resultado) {
				 mav.addObject("message", "Contacto agregado exitosamente");
				 mav.addObject("alertClass", "alert-success");
			 } else {
				 mav.addObject("message", "Error al agregar el contacto");
				 mav.addObject("alertClass", "alert-danger");
			 }
		 } else {
			 mav.addObject("message", "Número de cuenta no encontrado");
			 mav.addObject("alertClass", "alert-danger");
		 }
		    
		 List<Contacto> contactos = contactoService.obtenerContactos(usuario.getId_usuario());
		 mav.addObject("contactos", contactos);

		 return mav;
	}
	
	@PostMapping("/eliminar")
	public ModelAndView formEliminar(@RequestParam int idContacto,
			Authentication authentication) {
		ModelAndView mav = new ModelAndView("contacto.jsp");
		String cuentaAuth = authentication.getName();
		Usuario usuario = usuarioService.obtenerPorUser(cuentaAuth);
		
		boolean resultado= contactoService.eliminar(usuario.getId_usuario(), idContacto);
		if (resultado) {
			mav.addObject("messageTwo", "Contacto eliminado exitosamente");
			mav.addObject("alertClass", "alert-success");
		} else {
			mav.addObject("messageTwo", "Error al eliminar el contacto");
			mav.addObject("alertClass", "alert-danger");
		}
		    
		List<Contacto> contactos = contactoService.obtenerContactos(usuario.getId_usuario());
		mav.addObject("contactos", contactos);

		return mav;
	}
	

}
