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
