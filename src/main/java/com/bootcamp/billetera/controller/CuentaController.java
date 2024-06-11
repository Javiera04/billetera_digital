package com.bootcamp.billetera.controller;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	@Transactional
	public String postDeposito(@RequestParam("monto_deposito") int monto,
			Authentication authentication,
			Model model) {
		
		try {
			Usuario usuario = usuarioService.obtenerPorUser(authentication.getName());
			
			boolean depositoExitoso = cuentaService.depositar(authentication.getName(), monto);
			
			if(!depositoExitoso) {
				model.addAttribute("message", "Error al intentar realizar el depósito");
				model.addAttribute("alertClass", "alert-danger");
				return "deposito.jsp";
			}
			
			Date fechaTransaccion = new Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(fechaTransaccion.getTime());
			
			Transaccion transaccion = new Transaccion();
			transaccion.setId_cuenta_origen(usuario.getId_usuario());
			transaccion.setFecha_transaccion(timestamp);
			transaccion.setMonto(monto);
			transaccion.setId_tipo(1);
			
			boolean transaccionCreada = transaccionService.crear(transaccion);
			
			 if(!transaccionCreada) {
				 throw new Exception("Error al crear la transacción");
		     }
			
			model.addAttribute("message", "Depósito exitoso");
			model.addAttribute("alertClass", "alert-success");
		}catch(Exception e) {
			model.addAttribute("message", "Error al intentar realizar el depósito ");
			model.addAttribute("alertClass", "alert-danger");
			throw new RuntimeException(e);
		}
		
		return "deposito.jsp";
	}	
	
	@GetMapping("/retiro")
	public String getRetiro() {
		return "retiro.jsp";
	}
	
	@PostMapping("/retiro")
	@Transactional
	public String postRetiro(@RequestParam("monto_retiro") int monto,
			Authentication authentication,
			Model model) {
		
		try {
			Usuario usuario = usuarioService.obtenerPorUser(authentication.getName());
			
			boolean retiroExitoso = cuentaService.retirar(authentication.getName(), monto);
			
			if(!retiroExitoso) {
				model.addAttribute("message", "Error al intentar realizar el retiro");
				model.addAttribute("alertClass", "alert-danger");
				return "retiro.jsp";
			}
			
			Date fechaTransaccion = new Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(fechaTransaccion.getTime());
			
			Transaccion transaccion = new Transaccion();
			transaccion.setId_cuenta_origen(usuario.getId_usuario());
			transaccion.setFecha_transaccion(timestamp);
			transaccion.setMonto(monto);
			transaccion.setId_tipo(2);
			
			boolean transaccionCreada = transaccionService.crear(transaccion);
			
			if(!transaccionCreada) {
				throw new Exception("Error al crear la transacción");
			}
			
			model.addAttribute("message", "Retiro exitoso");
			model.addAttribute("alertClass", "alert-success");
		}catch(Exception e) {
			model.addAttribute("message", "Error al intentar realizar el retiro ");
			model.addAttribute("alertClass", "alert-danger");
			throw new RuntimeException(e);
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
	@Transactional
	public String postTransferir(@RequestParam("cuenta_destino") int idDestino, 
			@RequestParam("monto_transferencia") int monto,
			Authentication authentication,
			Model model) {
		String userAuth = authentication.getName();
		Cuenta cuentaOrigen = cuentaService.obtenerPorUser(userAuth);
		
		try {
			Usuario usuario = usuarioService.obtenerPorUser(authentication.getName());
			
			boolean transferenciaExitosa = cuentaService.transferir(cuentaOrigen.getId_cuenta(), idDestino, monto);
			
			if(!transferenciaExitosa) {
				model.addAttribute("message", "Error al intentar realizar la transferencia");
				model.addAttribute("alertClass", "alert-danger");
				return "transferir.jsp";
			}
			
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
			
			boolean transaccionCreada = transaccionService.crearTransaccionDestino(transaccion);
			
			if(!transaccionCreada) {
				throw new Exception("Error al crear la transacción");
			}
			
			model.addAttribute("message", "Transferencia realizada con éxito");
			model.addAttribute("alertClass", "alert-success");
		}catch (Exception e) {
            model.addAttribute("message", "Error al realizar la transferencia: " + e.getMessage());
            model.addAttribute("alertClass", "alert-danger");
            throw new RuntimeException(e);
		}
		
		return "transferir.jsp";
		
	}
	
	

}
