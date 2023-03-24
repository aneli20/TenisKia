package org.teniskia.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Catalogo;

public interface IntServiceCatalogos {
	
	public List<Catalogo> obtenerCatalogos();
	public void guardar (Catalogo catalogo);
	public Catalogo buscarPorId (Integer idCatalogo);
	public void eliminar (Integer idCatalogo);
	public int numeroCatalogos();
	Page<Catalogo>buscarTodas(Pageable page);
	

}
