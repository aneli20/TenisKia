package org.teniskia.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Catalogo;



public interface IntServiceCatalogos {

	public void guardar (Catalogo catalogo);
	public void eliminar (Integer id);
	List<Catalogo> buscarTodas();
	Catalogo buscarPorId(Integer idCatalogo);
	List<Catalogo> buscarDestacadas();
	Page<Catalogo> buscarTodas(Pageable page);
	List<Catalogo> buscarByExample(Example<Catalogo> example);
}
