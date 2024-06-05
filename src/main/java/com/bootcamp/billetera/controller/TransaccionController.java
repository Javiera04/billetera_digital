package com.bootcamp.billetera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transacciones")
public class TransaccionController {
	
	@GetMapping
	public String getTransaccion() {
		return "transaccion.jsp";
	}
}
