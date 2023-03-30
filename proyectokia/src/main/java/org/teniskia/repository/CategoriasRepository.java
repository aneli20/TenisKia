package org.teniskia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teniskia.entity.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
}
