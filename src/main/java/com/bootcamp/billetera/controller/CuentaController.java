package com.bootcamp.billetera.controller;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.billetera.model.Contacto;
import com.bootcamp.billetera.model.Cuenta;
import com.bootcamp.billetera.model.Transaccion;
import com.bootcamp.billetera.model.Usuario;
import com.bootcamp.billetera.service.ContactoService;
import com.bootcamp.billetera.service.CuentaService;
import com.bootcamp.billetera.service.TransaccionService;
import com.bootcamp.billetera.service.UsuarioService;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {
	
	private final CuentaService cuentaService;
	private final TransaccionService transaccionService;
	private final UsuarioService usuarioService;
	private final ContactoService contactoService;
	
	public CuentaController(CuentaService cuentaService,TransaccionService transaccionService, 
			UsuarioService usuarioService, ContactoService contactoService) {
		this.cuentaService=cuentaService;
		this.transaccionService=transaccionService;
		this.usuarioService=usuarioService;
		this.contactoService=contactoService;
	}
	
	@GetMapping("/deposito")
	public String getDeposito() {
		return "deposito.jsp";
	}
	
	@PostMapping("/deposito")
	public String postDeposito(@RequestParam("monto_deposito") int monto,
			Authentication authentication,
			Model model) {
		
		try {
			Usuario usuario = usuarioService.obtenerPorUser(authentication.getName());
			
			Date fechaTransaccion = new Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(fechaTransaccion.getTime());
			
			Transaccion transaccion = new Transaccion();
			transaccion.setId_cuenta_origen(usuario.getId_usuario());
			transaccion.setFecha_transaccion(timestamp);
			transaccion.setMonto(monto);
			transaccion.setId_tipo(1);
			
			transaccionService.crear(transaccion);
			
			cuentaService.depositar(authentication.getName(), monto);
			model.addAttribute("message", "Retiro exitoso");
		}catch(Exception e) {
			model.addAttribute("message", "Error al intentar realizar el retiro ");
		}
		
		return "deposito.jsp";
	}	
	
	@GetMapping("/retiro")
	public String getRetiro() {
		return "retiro.jsp";
	}
	
	@PostMapping("/retiro")
	public String postRetiro(@RequestParam("monto_retiro") int monto,
			Authentication authentication,
			Model model) {
		
		try {
			Usuario usuario = usuarioService.obtenerPorUser(authentication.getName());
			
			Date fechaTransaccion = new Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(fechaTransaccion.getTime());
			
			Transaccion transaccion = new Transaccion();
			transaccion.setId_cuenta_origen(usuario.getId_usuario());
			transaccion.setFecha_transaccion(timestamp);
			transaccion.setMonto(monto);
			transaccion.setId_tipo(2);
			
			transaccionService.crear(transaccion);
			
			cuentaService.retirar(authentication.getName(), monto);
			model.addAttribute("message", "Retiro exitoso");
		}catch(Exception e) {
			model.addAttribute("message", "Error al intentar realizar el retiro ");
		}
		
		return "retiro.jsp";
	}	
	
	@GetMapping("/transferir")
	public ModelAndView getTransferir(Authentication authentication) {
		ModelAndView mav = new ModelAndView("transferir.jsp");
		String cuentaAuth = authentication.getName();
		Usuario usuario = usuarioService.obtenerPorUser(cuentaAuth);
		
	
		List<Contacto> contacto = contactoService.obtenerContactos(usuario.getId_usuario());
	
		mav.addObject("contactos",contacto);
		
		return mav;
	}
	
	@PostMapping("/transferir")
	public String postTransferir(@RequestParam("cuenta_destino") int idDestino, 
			@RequestParam("monto_transferencia") int monto,
			Authentication authentication,
			Model model) {
		String userAuth = authentication.getName();
		Cuenta cuentaOrigen = cuentaService.obtenerPorUser(userAuth);
		
		try {
			Usuario usuario = usuarioService.obtenerPorUser(authentication.getName());
			
			Date fechaTransaccion = new Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(fechaTransaccion.getTime());
			
			Transaccion transaccion = new Transaccion();
			transaccion.setId_cuenta_origen(usuario.getId_usuario());
			transaccion.setId_cuenta_destino(idDestino);
			transaccion.setFecha_transaccion(timestamp);
			transaccion.setMonto(monto);
			transaccion.setId_tipo(3);
			
			System.out.println(idDestino);
			System.out.println(transaccion);
			
			transaccionService.crearTransaccionDestino(transaccion);
			
			cuentaService.transferir(cuentaOrigen.getId_cuenta(), idDestino, monto);
			model.addAttribute("message", "Transferencia realizada con éxito");
		}catch (Exception e) {
            model.addAttribute("message", "Error al realizar la transferencia: " + e.getMessage());
        }
		
		return "transferir.jsp";
		
	}
	
	

}
