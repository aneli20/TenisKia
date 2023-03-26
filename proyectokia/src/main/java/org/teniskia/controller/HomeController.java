package org.teniskia.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.teniskia.entity.Perfil;
import org.teniskia.entity.Usuario;
import org.teniskia.service.IntServiceCatalogos;
import org.teniskia.service.IntServiceUsuarios;

@Controller
public class HomeController {
	@Autowired
	private IntServiceUsuarios serviceUsuarios;
	
	
	@Autowired
	private IntServiceCatalogos serviceCatalogos;
	
	@PostMapping("/guardar")
	public String guardar(Usuario usuario) {
		usuario.setFechaRegistro(LocalDate.now());
		usuario.setEstatus(1);
		usuario.setPassword("{noop}" + usuario.getPassword());
		Perfil per = new Perfil();
			per.setId(3);
			usuario.agregar(per);
			serviceUsuarios.agregar(usuario);
		
		return "home";
	}
	
	@GetMapping("/singup")
	public String singup() {
		return "formRegistro";
	}

	
	@GetMapping("/")
	public String mostrarIndex(Model model) {
		model.addAttribute("catalogos", serviceCatalogos.obtenerCatalogos());
		return "home";
	}
	
}
