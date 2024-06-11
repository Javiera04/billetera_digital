package com.bootcamp.billetera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model, @RequestParam(name="error", required = false) String error) {
		if (error != null) {
	        model.addAttribute("message", "Credenciales incorrectas. Por favor, inténtelo de nuevo.");
	        model.addAttribute("alertClass", "alert-danger");
	    }
	    return "login.jsp";
	}
}
