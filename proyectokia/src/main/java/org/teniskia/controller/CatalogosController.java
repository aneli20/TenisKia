package org.teniskia.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teniskia.entity.Catalogo;
import org.teniskia.service.CatalogosService;
import org.teniskia.service.CategoriasService;
import org.teniskia.util.Utileria;

@Controller
@RequestMapping(value="/catalogos")
public class CatalogosController {
	
	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private CatalogosService serviceCatalogos;
    
    @Autowired
   	private CategoriasService serviceCategorias;
	  
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	List<Catalogo> lista = serviceCatalogos.buscarTodas();
    	model.addAttribute("catalogos", lista);
		return "catalogos/listCatalogos";
	}
    
    /**
	 * Metodo que muestra la lista de vacantes con paginacion
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Catalogo> lista = serviceCatalogos.buscarTodas(page);
		model.addAttribute("catalogos", lista);
		return "catalogos/listCatalogos";
	}
    
	/**
	 * Método que muestra el formulario para crear una nueva Vacante
	 * @param vacante
	 * @return
	 */
	@GetMapping("/create")
	public String crear(@ModelAttribute Catalogo catalogo) {		
		return "catalogos/formCatalogo";
	}
	
	/**
	 * Método que guarda la Vacante en la base de datos
	 * @param vacante
	 * @param result
	 * @param model
	 * @param multiPart
	 * @param attributes
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(@ModelAttribute Catalogo catalogo, BindingResult result, Model model,
			@RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes attributes ) {	
		
		if (result.hasErrors()){
			
			System.out.println("Existieron errores");
			return "catalogos/formCatalogo";
		}	
		
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			//String ruta = "c:/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen!=null){ // La imagen si se subio				
				catalogo.setImagen(nombreImagen); // Asignamos el nombre de la imagen
			}	
		}
				
		// Guadamos el objeto vacante en la bd
		serviceCatalogos.guardar(catalogo);
		attributes.addFlashAttribute("msg", "Los datos del catalogo fueron guardados!");
			
		//return "redirect:/vacantes/index";
		return "redirect:/catalogos/indexPaginate";		
	}
	
	/**
	 * Método para renderizar la vista de los Detalles para una  determinada Vacante
	 * @param idVacante
	 * @param model
	 * @return
	 */
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idCatalogo, Model model) {		
		Catalogo catalogo = serviceCatalogos.buscarPorId(idCatalogo);			
		model.addAttribute("catalogo", catalogo);
		return "detalle";
	}
	
	/**
	 * Método que renderiza el formulario HTML para editar una vacante
	 * @param idVacante
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idCatalogo, Model model) {		
		Catalogo catalogo = serviceCatalogos.buscarPorId(idCatalogo);			
		model.addAttribute("catalogo", catalogo);
		return "catalogos/formCatalogo";
	}
	
	/**
	 * Método que elimina una Vacante de la base de datos
	 * @param idVacante
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCatalogo, RedirectAttributes attributes) {
		
		// Eliminamos la vacante.
		serviceCatalogos.eliminar(idCatalogo);
			
		attributes.addFlashAttribute("msg", "El catalogo fue eliminado!.");
		//return "redirect:/vacantes/index";
		return "redirect:/catalogos/indexPaginate";
	}
	
	/**
	 * Agregamos al Model la lista de Categorias: De esta forma nos evitamos agregarlos en los metodos
	 * crear y editar. 
	 * @return
	 */	
	@ModelAttribute
	public void setGenericos(Model model){
		model.addAttribute("categorias", serviceCategorias.buscarTodas());	
	}
	
	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
