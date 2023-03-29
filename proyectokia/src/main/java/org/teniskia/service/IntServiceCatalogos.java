package org.teniskia.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Catalogo;

public interface IntServiceCatalogos {
	
	public List<Catalogo> obtenerCatalogos();
	public void guardar (Catalogo catalogo);
	public void eliminar (Integer id);
	public Catalogo buscarPorId (Integer idCatalogo);
	public int totalCatalogos();
	Page<Catalogo>buscarTodas(Pageable page);
	

}
