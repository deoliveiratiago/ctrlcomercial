package ga.deoliveiratiago.ctrlcomercial;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ga.deoliveiratiago.ctrlcomercial.domain.Categoria;
import ga.deoliveiratiago.ctrlcomercial.domain.Cidade;
import ga.deoliveiratiago.ctrlcomercial.domain.Cliente;
import ga.deoliveiratiago.ctrlcomercial.domain.Endereco;
import ga.deoliveiratiago.ctrlcomercial.domain.Estado;
import ga.deoliveiratiago.ctrlcomercial.domain.Produto;
import ga.deoliveiratiago.ctrlcomercial.domain.enums.TipoCliente;
import ga.deoliveiratiago.ctrlcomercial.repositories.CategoriaRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.CidadeRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.ClienteRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.EnderecoRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.EstadoRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.ProdutoRepository;

@SpringBootApplication
public class CtrlcomercialApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CtrlcomercialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Parana");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Curitiba", est2);
		Cidade c3 = new Cidade(null, "Londrina", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "98749283", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("2937482", "293489238"));
		
		Endereco en1 = new Endereco(null, "Rua 1", "2374892", "apto 234", "Centro", "238942", cli1, c1);
		Endereco en2 = new Endereco(null, "Rua 2", "34234", "apto 346", "Centro", "74422", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(en1, en2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(en1, en2));
		
		
	}

}
