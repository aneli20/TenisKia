package org.teniskia.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teniskia.entity.Catalogo;

@Service
public class CatalogoServiceImp implements IntServiceCatalogos{
	private List<Catalogo> catalogos = null;
	
	public CatalogoServiceImp() {
		catalogos = new LinkedList<>();
		
		Catalogo c1 = new Catalogo();
		
		c1.setId(1);
		c1.setNombre("nike");
		c1.setDescripcion("tenis de futbol");
		c1.setPrecio(1050.50);
		c1.setTalla(2.25);
		
		catalogos.add(c1);
		
	}
	
	
	@Override
	public List<Catalogo> obtenerCatalogos(){
		return catalogos;
	}
	
	@Override
	public void guardar(Catalogo catalogo) {
		catalogos.add(catalogo);
	}
	
	@Override
	public Catalogo buscarPorId(Integer idCatalogo) {
		for(Catalogo cata : catalogos) {
			if(cata.getId().compareTo(idCatalogo)==0) {
				return cata;
				
			}
		}
		return null;
	}
	
	@Override
	public void eliminar(Integer idCatalogo) {
		catalogos.remove(buscarPorId(idCatalogo));
	}
	
	@Override
	public int numeroCatalogos() {
		return catalogos.size();
	}
	
	@Override
	public Page<Catalogo> buscarTodas(Pageable page){
		return null;
	}

}
