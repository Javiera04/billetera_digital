package com.bootcamp.billetera.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.billetera.model.Cuenta;
import com.bootcamp.billetera.model.Usuario;
import com.bootcamp.billetera.service.CuentaService;
import com.bootcamp.billetera.service.UsuarioService;



@Controller
@RequestMapping("/home")
public class HomeController {

	private final UsuarioService userService;
	private final CuentaService cuentaService;
	
	public HomeController(UsuarioService userService,CuentaService cuentaService){
		this.userService= userService;
		this.cuentaService=cuentaService;
	}
	
	/*
	@GetMapping
	public String home() {
		return "home.jsp";
	}*/
	
	
	@GetMapping
	public ModelAndView home(Authentication authentication) {
		ModelAndView mav= new ModelAndView("home.jsp");
		Usuario usuario=userService.obtenerPorUser(authentication.getName());
		Cuenta cuenta=cuentaService.obtenerPorUser(authentication.getName());
		
		mav.addObject("user",usuario);
		mav.addObject("cuenta", cuenta);
		
		return mav;
	}
}
