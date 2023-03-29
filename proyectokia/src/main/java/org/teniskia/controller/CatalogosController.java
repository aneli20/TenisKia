package org.teniskia.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teniskia.entity.Catalogo;
import org.teniskia.service.IntServiceCatalogos;
import org.teniskia.service.IntServiceVentas;
import org.teniskia.util.Utileria;



@RequestMapping("/catalogos")
@Controller
public class CatalogosController {
	
	@Value("${proyecto.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IntServiceCatalogos serviceCatalogos;
	
	@Autowired
	private IntServiceVentas serviceVentas;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Catalogo>catalogos = serviceCatalogos.obtenerCatalogos();
		model.addAttribute("total", serviceCatalogos.totalCatalogos());
		return "catalogos/listaCatalogos";
	}
	
	
	@GetMapping(value = "/indexPaginado")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Catalogo> lista = serviceCatalogos.buscarTodas(page);
	model.addAttribute("catalogos", lista);
	model.addAttribute("total", serviceCatalogos.obtenerCatalogos().size());
	return "catalogos/listaCatalogos";
	}
	
	@GetMapping("/nueva")
	public String nuevaCata(Catalogo catalogo,Model model) {
		model.addAttribute("ventas", serviceVentas.obtenerVentas());
		return "catalogos/formCatalogo";
	}
	
	@PostMapping("/guardar")
	public String guardar(Catalogo catalogo, BindingResult result, @RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes model) {
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			model.addAttribute("ventas", serviceVentas.obtenerVentas());
			return "catalogos/formCatalogo";
		}
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
			catalogo.setImagen(nombreImagen); 
			}
			}
		if(catalogo.getId()==null) model.addFlashAttribute("msg", "teni guardada");
		else model.addFlashAttribute("msg", "tenis  modificado");

		serviceCatalogos.guardar(catalogo);
		return "redirect:/catalgos/indexPaginado";
	}
	
	
	@GetMapping("/buscar")
	public String modificarCa(@RequestParam("id") int idCatalogo, Model model) {
		Catalogo catalogo = serviceCatalogos.buscarPorId(idCatalogo);
		model.addAttribute("ventas", serviceVentas.obtenerVentas());
		model.addAttribute("catalogo", catalogo);
		return "catalogos/formCatalogo";
	}

	
	
	@GetMapping("/eliminar")
	public String eliminarCata(@RequestParam("id") int idCatalogo, RedirectAttributes model) {
		serviceCatalogos.eliminar(idCatalogo);
		model.addFlashAttribute("msg", "Catalogo Eliminada");
		return "redirect:/catalogos/index";
	}
	
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id") int idCatalogo, Model model) {
		Catalogo catalogo = serviceCatalogos.buscarPorId(idCatalogo);
		model.addAttribute("catalogo", catalogo);
		return "catalogos/detalle";
	}

	
	
	
	
	
	

}
