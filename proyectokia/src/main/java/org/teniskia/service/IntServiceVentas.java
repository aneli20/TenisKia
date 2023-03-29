package org.teniskia.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Venta;

public interface IntServiceVentas {
	
	public List<Venta> obtenerVentas();
	public void guardar (Venta venta);
	public Venta buscarPorId (Integer idVenta);
	public void eliminar(Integer idVenta);
	public int numeroVentas();
	Page<Venta>buscarTodas(Pageable page);

}
