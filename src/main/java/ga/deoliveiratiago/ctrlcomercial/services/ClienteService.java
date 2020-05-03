package ga.deoliveiratiago.ctrlcomercial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.deoliveiratiago.ctrlcomercial.domain.Cliente;
import ga.deoliveiratiago.ctrlcomercial.repositories.ClienteRepository;
import ga.deoliveiratiago.ctrlcomercial.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
