package org.teniskia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Perfil;
import org.teniskia.repository.PerfilRepository;
import org.teniskia.service.IntServicePerfil;

public class PerfilServiceJpa implements IntServicePerfil {
	@Autowired
	private PerfilRepository repoPerfil; 
	@Override
	public List<Perfil> obtenerPerfil() {
		return repoPerfil.findAll();
	}

	@Override
	public void guardar(Perfil perfil) {
		repoPerfil.save(perfil);
	}

	@Override
	public Perfil buscarPorId(Integer idPerfil) {
		Optional<Perfil> optional =  repoPerfil.findById(idPerfil);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idPerfil) {
		repoPerfil.deleteById(idPerfil);
	}

	@Override
	public int numeroPerfil() {
		return (int) repoPerfil.count();
	}
	
	@Override
	public Page<Perfil>buscarTodas(Pageable page){
		return repoPerfil.findAll(page);
	}

}
