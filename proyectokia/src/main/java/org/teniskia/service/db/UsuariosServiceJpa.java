package org.teniskia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teniskia.entity.Usuario;
import org.teniskia.repository.UsuariosRepository;
import org.teniskia.service.IntServiceUsuarios;

@Service
@Primary
public class UsuariosServiceJpa implements IntServiceUsuarios{
	
	@Autowired
	private UsuariosRepository repoUsuarios;
	@Override

	public List<Usuario> obtenerUsuarios(){
		return repoUsuarios.findAll();
	}
		
	@Override
	public void guardar(Usuario usuario) {
		repoUsuarios.save(usuario);
		
	}
	
	@Override
	public void eliminar (Integer idUsuario) {
		repoUsuarios.deleteById(idUsuario);
	}
	
	@Override 
	public Usuario buscarPorId(Integer idUsuario) {
		Optional<Usuario> optional = repoUsuarios.findById(idUsuario);
			if(optional.isPresent()) {
			return optional.get();
		}
	
	return null;

}

@Override 
public long totalUsuarios() {
	return (long) repoUsuarios.count();
}

@Override
public Page<Usuario> buscarTodas(Pageable page){
	return repoUsuarios.findAll(page);
}


}
