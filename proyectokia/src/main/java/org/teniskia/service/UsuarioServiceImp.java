package org.teniskia.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.teniskia.entity.Usuario;

public class UsuarioServiceImp implements IntServiceUsuarios {
	
	private List<Usuario> usuarios = null;
	public UsuarioServiceImp( ) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		try {
		usuarios = new LinkedList <>();
		Usuario u1 = new Usuario ();
	
		
		
		u1.setId(1);
		u1.setNombre("Kiyo");
		u1.setEmail("kiyoshijuarez@gmail.com");
		u1.setUsername("grillo");
		u1.setPassword("grillo18");
		u1.setEstatus(2);
		u1.setFechaRegistro(LocalDate.parse("02/09/2023", formato));
		
		usuarios.add(u1);
		
	}catch (DateTimeParseException ex) {
		System.out.println(ex.getMessage());
	}
}

	@Override 
	public List<Usuario> obtenerUsuarios(){
		return usuarios;
		
	}
	
	@Override
	public void guardar(Usuario usuario) {
		usuarios.add(usuario);
		
	}
	
	@Override
	public void eliminar (Integer idUsuario) {
		usuarios.remove(buscarPorId(idUsuario));
	}
	
	@Override 
	public Usuario buscarPorId(Integer idUsuario) {
		for(Usuario u : usuarios) {
			if(u.getId()== idUsuario) {
			return u;
		}
	}
	return null;

}

@Override 
public long totalUsuarios() {
	
	return usuarios.size();
}

@Override
public Page<Usuario> buscarTodas(Pageable page){
	return null;
}

}