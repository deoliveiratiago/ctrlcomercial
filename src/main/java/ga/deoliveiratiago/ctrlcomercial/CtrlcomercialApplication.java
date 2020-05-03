package ga.deoliveiratiago.ctrlcomercial;

import java.text.SimpleDateFormat;
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
import ga.deoliveiratiago.ctrlcomercial.domain.ItemPedido;
import ga.deoliveiratiago.ctrlcomercial.domain.Pagamento;
import ga.deoliveiratiago.ctrlcomercial.domain.PagamentoBoleto;
import ga.deoliveiratiago.ctrlcomercial.domain.PagamentoCartao;
import ga.deoliveiratiago.ctrlcomercial.domain.Pedido;
import ga.deoliveiratiago.ctrlcomercial.domain.Produto;
import ga.deoliveiratiago.ctrlcomercial.domain.enums.EstadoPagamento;
import ga.deoliveiratiago.ctrlcomercial.domain.enums.TipoCliente;
import ga.deoliveiratiago.ctrlcomercial.repositories.CategoriaRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.CidadeRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.ClienteRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.EnderecoRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.EstadoRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.ItemPedidoRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.PagamentoRepository;
import ga.deoliveiratiago.ctrlcomercial.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, data.parse("30/04/2018 12:01"), cli1, en2);
		Pedido ped2 = new Pedido(null, data.parse("03/06/2013 15:21"), cli1, en1);
		
		Pagamento pgto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 2);
		ped1.setPagamento(pgto1);
		Pagamento pgto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, data.parse("03/09/2016 11:41"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 10.0, 2, 90.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 50.0, 2, 800.0);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
