package org.teniskia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teniskia.entity.Usuario;

public interface UsuariosRepository  extends JpaRepository<Usuario, Integer>{

}
