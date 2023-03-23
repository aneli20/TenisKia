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
import org.teniskia.entity.Catalogo;
import org.teniskia.service.IntServiceCatalogos;

@RequestMapping("/catalogos")
@Controller
public class CatalogosController {
	@Autowired
	private IntServiceCatalogos serviceCatalogo;
	
	@GetMapping("/buscar")
	public String modificarCa(@RequestParam("id") int idCatalogo, Model model) {
		Catalogo catalogo = serviceCatalogo.buscarPorId(idCatalogo);
		model.addAttribute("catalogo", catalogo);
		return "catalogos/formCatalogo";
	}
	@PostMapping("/guardar")
	public String guardar(Catalogo catalogo/*, RedirectAttributes model*/) {
		//categoria.setId(serviceCategorias.obtenerCategorias().size()+1);
		//serviceCategorias.guardar(catalogo);
		/*categoria.setId(serviceCategorias.obtenerCategorias().size()+1);*/
		serviceCatalogo.guardar(catalogo);
		return "redirect:/catalogos/index";
		/*if(categoria.getId()==null) {
			categoria.setId(serviceCategorias.obtenerCategorias().size()+1);
			serviceCategorias.guardar(catalogo);
		}else {
			int index = serviceCategorias.obtenerCategorias().size()-1;
			//System.out.println("index = "+index);
			serviceCategorias.obtenerCategorias().set(index, categoria);
		}
		model.addFlashAttribute("msg", "Categoria agregada con exito!");
		return "redirect:/categorias/index";*/
	}
	@GetMapping("/nueva")
	public String nuevaCat(Catalogo catalogo) {
		return "catalogos/formCatalogo";
	}
	
	@GetMapping("/eliminar")
	public String eliminarCa(@RequestParam("id") int idCatalogo, RedirectAttributes model) {
		serviceCatalogo.eliminar(idCatalogo);
		model.addFlashAttribute("msg", "Catalogo Eliminada");
		return "redirect:/catalogos/index";
	}

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Catalogo>catalogos = serviceCatalogo.obtenerCatalogos();
		model.addAttribute("catalogos", catalogos);
		return "catalogos/listaCatalogos";
	}
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Catalogo> lista = serviceCatalogo.buscarTodas(page);
	model.addAttribute("catalogos", lista);
	model.addAttribute("total", serviceCatalogo.obtenerCatalogos().size());
	return "catalogos/listaCatalogos";
	}
	
	
	

}
