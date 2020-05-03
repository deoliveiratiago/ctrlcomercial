package ga.deoliveiratiago.ctrlcomercial.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ga.deoliveiratiago.ctrlcomercial.domain.Pedido;
import ga.deoliveiratiago.ctrlcomercial.repositories.PedidoRepository;
import ga.deoliveiratiago.ctrlcomercial.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
