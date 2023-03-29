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
import org.teniskia.entity.Venta;
import org.teniskia.service.IntServiceVentas;

@RequestMapping("/ventas")
@Controller

public class VentasController {
	@Autowired
	private IntServiceVentas serviceVentas;
	
		@GetMapping("/buscar")
		public String modificarVen(@RequestParam("id") int idVenta, Model model) {
			Venta venta = serviceVentas.buscarPorId(idVenta);
			model.addAttribute("venta", venta);
			return "ventas/formVenta";
		}
		
		@PostMapping("/guardar")
		public String guardar(Venta venta/*, RedirectAttributes model*/) {
			//categoria.setId(serviceCategorias.obtenerCategorias().size()+1);
			//serviceCategorias.guardar(venta);
			/*categoria.setId(serviceCategorias.obtenerCategorias().size()+1);*/
			serviceVentas.guardar(venta);
			return "redirect:/ventas/index";
			/*if(categoria.getId()==null) {
				categoria.setId(serviceCategorias.obtenerCategorias().size()+1);
				serviceCategorias.guardar(venta);
			}else {
				int index = serviceCategorias.obtenerCategorias().size()-1;
				//System.out.println("index = "+index);
				serviceCategorias.obtenerCategorias().set(index, categoria);
			}
			model.addFlashAttribute("msg", "Categoria agregada con exito!");
			return "redirect:/categorias/index";*/
		}
		
		@GetMapping("/nueva")
		public String nuevaVen(Venta venta) {
			return "ventas/formVenta";
		}
		
		@GetMapping("/eliminar")
		public String eliminarVen(@RequestParam("id") int idVenta, RedirectAttributes model) {
			serviceVentas.eliminar(idVenta);
			model.addFlashAttribute("msg", "Venta Eliminada");
			return "redirect:/ventas/index";
		}

		@GetMapping("/index")
		public String mostrarIndex(Model model) {
			List<Venta>ventas = serviceVentas.obtenerVentas();
			model.addAttribute("ventas", ventas);
			return "ventas/listaVentas";
		}
		
		@GetMapping(value = "/indexPaginado")
		public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Venta> lista = serviceVentas.buscarTodas(page);
		model.addAttribute("ventas", lista);
		model.addAttribute("total", serviceVentas.obtenerVentas().size());
		return "ventas/listaVentas";
		}
		
	}


