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

import jakarta.servlet.http.HttpServletRequest;

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
			
		
		return "home";
	}
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	
	
	@GetMapping("/")
	public String mostrarIndex(Model model) {
		model.addAttribute("catalogos", serviceCatalogos.obtenerCatalogos());
		return "home";
	}
	
	@GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
	}
	
	@GetMapping("/login")
	public String mostrarLogin(Usuario usuario) {
		return "formLogin";
	}
	
	
	
}
