package org.teniskia.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Perfil;

public interface IntServicePerfil {
	public List<Perfil> obtenerPerfil();
	public void guardar (Perfil perfil);
	public Perfil buscarPorId (Integer idPerfil);
	public void eliminar (Integer idPerfil);
	public int numeroPerfil();
	Page<Perfil>buscarTodas(Pageable page);

}
