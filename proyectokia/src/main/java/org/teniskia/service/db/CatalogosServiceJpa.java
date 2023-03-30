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
import org.teniskia.service.CatalogosService;



@Service
@Primary
public class CatalogosServiceJpa  implements CatalogosService{
	@Autowired
	private CatalogosRepository catalogosRepo;

	@Override
	public void guardar(Catalogo catalogo) {
		catalogosRepo.save(catalogo);
	}

	@Override
	public void eliminar(Integer idCatalogo) {
		catalogosRepo.deleteById(idCatalogo);
	}

	@Override
	public List<Catalogo> buscarTodas() {		
		return catalogosRepo.findAll();
	}

	@Override
	public Catalogo buscarPorId(Integer idCatalogo) {
		Optional<Catalogo> optional = catalogosRepo.findById(idCatalogo);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Catalogo> buscarDestacadas() {
		return catalogosRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Con Existencia");
	}

	@Override
	public Page<Catalogo> buscarTodas(Pageable page) {		
		return catalogosRepo.findAll(page);
	}

	@Override
	public List<Catalogo> buscarByExample(Example<Catalogo> example) {
		return catalogosRepo.findAll(example);
	}
		

}
