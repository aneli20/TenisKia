package org.teniskia.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Usuario;

public interface IntServiceUsuarios {
	
	public List<Usuario> obtenerUsuarios();
	public void guardar (Usuario usuario);
	public void eliminar (Integer idUsuario);
	public Usuario buscarPorId (Integer idUsuario);
	public long totalUsuarios();
	Page<Usuario>buscarTodas(Pageable page);
	

}
