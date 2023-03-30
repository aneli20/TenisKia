package org.teniskia.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Categoria;

public interface CategoriasService {
	void guardar(Categoria categoria);
	void eliminar(Integer idCategoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	Page<Categoria> buscarTodas(Pageable page);
}
