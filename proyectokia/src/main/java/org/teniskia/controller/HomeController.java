package org.teniskia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.teniskia.service.IntServiceCatalogos;

@Controller
public class HomeController {
	
	@Autowired
	private IntServiceCatalogos serviceCatalogos;

	
	@GetMapping("/")
	public String mostrarIndex(Model model) {
		model.addAttribute("catalogos", serviceCatalogos.obtenerCatalogos());
		return "home";
	}
	
}
