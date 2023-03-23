package org.teniskia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teniskia.entity.Venta;
import org.teniskia.repository.VentasRepository;
import org.teniskia.service.IntServiceVentas;

@Service
@Primary
public class VentasServiceJpa implements IntServiceVentas{
	
	@Autowired
	private VentasRepository repoVentas;
	@Override
	public List<Venta> obtenerVentas(){
		return repoVentas.findAll();
	}
	
	@Override 
	public void guardar (Venta venta) {
		repoVentas.save(venta);
	}
	
	
	
	@Override 
	public Venta buscarPorId(Integer idVenta) {
	 Optional<Venta> optional = repoVentas.findById(idVenta);
		if(optional.isPresent()) {
			return optional.get();
			
		
	}
	return null;
	}
	@Override
	public void eliminar(Integer idVenta) {
		repoVentas.deleteById(idVenta);
	}
	
	@Override
	public int numeroVenta() {
		return (int) repoVentas.count();
	}
	
	@Override
	public Page<Venta> buscarTodas (Pageable page){
		return repoVentas.findAll(page);
	}

	

}
