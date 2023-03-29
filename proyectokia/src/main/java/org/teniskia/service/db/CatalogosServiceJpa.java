package org.teniskia.service.db;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.teniskia.entity.Catalogo;
import org.teniskia.repository.CatalogosRepository;
import org.teniskia.service.IntServiceCatalogos;



@Service
@Primary
public class CatalogosServiceJpa  implements IntServiceCatalogos{
	
	@Autowired
	private  CatalogosRepository repoCatalogos;
	
	
	@Override
	public void guardar(Catalogo catalogo) {
		repoCatalogos.save(catalogo);
	}

	@Override
	public void eliminar(Integer idCatalogo) {
		repoCatalogos.deleteById(idCatalogo);
	}

	@Override
	public List<Catalogo> buscarTodas() {		
		return repoCatalogos.findAll();
	}

	@Override
	public Catalogo buscarPorId(Integer idCatalogo) {
		Optional<Catalogo> optional = repoCatalogos.findById(idCatalogo);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Catalogo> buscarDestacadas() {
		return repoCatalogos.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
	}

	@Override
	public Page<Catalogo> buscarTodas(Pageable page) {		
		return repoCatalogos.findAll(page);
	}

	@Override
	public List<Catalogo> buscarByExample(Example<Catalogo> example) {
		return repoCatalogos.findAll(example);
	}
		

}
