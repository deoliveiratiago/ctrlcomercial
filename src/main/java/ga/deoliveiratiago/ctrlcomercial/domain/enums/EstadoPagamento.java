package ga.deoliveiratiago.ctrlcomercial.domain.enums;

public enum EstadoPagamento {

	PENDENTE(0, "Pendente"),
	QUITADO(1, "Quitado"),
	CANCELADO(2, "Cancelado");

	private Integer id;
	private String descricao;

	private EstadoPagamento(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer id) {

		if (id == null) {
			return null;
		}

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (id.equals(x.getId())) {
				return x;
			}	
		}

		throw new IllegalArgumentException("Invalid Id: " + id);

	}
}
