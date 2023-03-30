package org.teniskia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.teniskia.entity.Catalogo;

public interface CatalogosRepository extends JpaRepository<Catalogo, Integer> {
	
	List<Catalogo> findByEstatus(String estatus);
	
	List<Catalogo> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	
	List<Catalogo> findByPrecioBetweenOrderByPrecioDesc(double s1, double s2);
	
	List<Catalogo> findByEstatusIn(String[] estatus);

}
