package ga.deoliveiratiago.ctrlcomercial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.deoliveiratiago.ctrlcomercial.domain.Categoria;
import ga.deoliveiratiago.ctrlcomercial.repositories.CategoriaRepository;
import ga.deoliveiratiago.ctrlcomercial.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
}
