package org.teniskia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teniskia.entity.Perfil;
import org.teniskia.service.IntServicePerfil;

@RequestMapping("/perfiles")
@Controller
public class PerfilController {
	@Autowired
	private IntServicePerfil servicePerfil;
	
	/*@PostMapping("/guardar")
	public String guardarCat(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion) {
		Categoria cat = new Categoria();
		cat.setId(serviceCategorias.obtenerCategorias().size()+1);
		cat.setNombre(nombre);
		cat.setDescripcion(descripcion);
		serviceCategorias.guardar(cat);
		return "redirect:/categorias/index";
	}*/
	
	@GetMapping("/buscar")
	public String modificarPer(@RequestParam("id") int idPerfil, Model model) {
		Perfil perfil = servicePerfil.buscarPorId(idPerfil);
		model.addAttribute("perfil", perfil);
		return "perfiles/formPerfil";
	}
	
	@PostMapping("/guardar")
	public String guardar(Perfil perfil/*, RedirectAttributes model*/) {
		//categoria.setId(serviceCategorias.obtenerCategorias().size()+1);
		//serviceCategorias.guardar(categoria);
		/*categoria.setId(serviceCategorias.obtenerCategorias().size()+1);*/
		servicePerfil.guardar(perfil);
		return "redirect:/perfiles/index";
		/*if(categoria.getId()==null) {
			categoria.setId(serviceCategorias.obtenerCategorias().size()+1);
			serviceCategorias.guardar(categoria);
		}else {
			int index = serviceCategorias.obtenerCategorias().size()-1;
			//System.out.println("index = "+index);
			serviceCategorias.obtenerCategorias().set(index, categoria);
		}
		model.addFlashAttribute("msg", "Categoria agregada con exito!");
		return "redirect:/categorias/index";*/
	}
	
	@GetMapping("/nueva")
	public String nuevoPer(Perfil perfil) {
		return "perfiles/formPerfil";
	}
	
	@GetMapping("/eliminar")
	public String eliminarPer(@RequestParam("id") int idPerfil, RedirectAttributes model) {
		servicePerfil.eliminar(idPerfil);
		model.addFlashAttribute("msg", "Perfil Eliminado");
		return "redirect:/perfiles/index";
	}

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Perfil>perfil = servicePerfil.obtenerPerfil();
		model.addAttribute("perfil", perfil);
		return "perfiles/listaPerfiles";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Perfil> lista = servicePerfil.buscarTodas(page);
	model.addAttribute("perfil", lista);
	model.addAttribute("total", servicePerfil.obtenerPerfil().size());
	return "perfiles/listaPerfiles";
	}

}
