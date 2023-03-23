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
	
	public CatalogoServiceImp () {
		catalogos = new LinkedList<>();
		
		Catalogo c1 = new Catalogo();
		Catalogo c2 = new Catalogo();
		
		c1.setId(1);
		c1.setNombre("Nike");
		c1.setDescripcion("Tenis de futbol");
		c1.setPrecio(1150.0);
		c1.setTalla(25.5);
		
		c2.setId(1);
		c2.setNombre("Adidas");
		c2.setDescripcion("Tenis de futbol");
		c2.setPrecio(1250.0);
		c2.setTalla(23.5);
		
		catalogos.add(c1);
		catalogos.add(c2);
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
		for(Catalogo cat : catalogos) {
			if(cat.getId().compareTo(idCatalogo)==0) {
				return cat;
				
			}
		}
		return null;
	}
	
	@Override
	public void eliminar(Integer idCatalogo) {
		catalogos.remove(buscarPorId(idCatalogo));
	}
	
	@Override
	public int numeroCatalogo() {
		return catalogos.size();
	}
	
	@Override
	public Page<Catalogo> buscarTodas(Pageable page){
		return null;
	}

}
