package org.teniskia.service;

import java.util.List;


import org.teniskia.entity.Usuario;

public interface IntServiceUsuarios {
	
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
	List<Usuario> buscarRegistrados();
	Usuario buscarPorId(Integer idUsuario);
	Usuario buscarPorUsername(String username);
	int bloquear(int idUsuario);
	int activar(int idUsuario);
	void agregar(Usuario usuario);
	

}
