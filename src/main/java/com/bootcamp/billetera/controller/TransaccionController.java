package com.bootcamp.billetera.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.billetera.model.Transaccion;
import com.bootcamp.billetera.service.TransaccionService;

@Controller
@RequestMapping("/transacciones")
public class TransaccionController {
	
	private final TransaccionService transaccionService;
	
	public TransaccionController(TransaccionService transaccionService) {
		this.transaccionService=transaccionService;
	}
	
	@GetMapping
	public ModelAndView obtenerTransacciones(Authentication authentication) {
		ModelAndView mav = new ModelAndView("transaccion.jsp");
	
		List<Transaccion> transacciones = transaccionService.obtenerTransacciones(authentication.getName());
	
		mav.addObject("transaccion",transacciones);
		
		return mav;
	}
}
