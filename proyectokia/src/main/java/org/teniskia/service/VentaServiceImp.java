package org.teniskia.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teniskia.entity.Venta;

@Service
public class VentaServiceImp implements IntServiceVentas {
	private List<Venta> ventas  ;
	
	public VentaServiceImp () {
		ventas = new LinkedList<>();
		
		
		Venta v1 = new Venta ();
		Venta v2 = new Venta ();
		
		v1.setId(1);
		v1.setDescripcion("venta de tenis");
		v1.setPrecio(1150.0);
		v1.setTalla(23.00);
		
		ventas.add(v1);
		ventas.add(v2);
				
	}
	
	@Override
	public List<Venta> obtenerVentas(){
		return ventas;
	}
	
	@Override 
	public void guardar (Venta venta) {
		ventas.add(venta);
	}
	
	
	@Override 
	public Venta buscarPorId(Integer idVenta) {
	for (Venta ve : ventas) {
		if(ve.getId()==idVenta) {
			return ve;
			
		}
	}
	return null;
	}
	@Override
	public void eliminar(Integer idVenta) {
		ventas.remove(buscarPorId(idVenta));
	}
	
	@Override
	public int numeroVentas() {
		return ventas.size();
	}
	
	@Override
	public Page<Venta> buscarTodas (Pageable page){
		return null;
	}

}
