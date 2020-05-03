package ga.deoliveiratiago.ctrlcomercial.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(0, "Pessoa Fisica"),
	PESSOAJURIDICA(1, "Pessoa Juridica");

	private Integer id;
	private String descricao;

	private TipoCliente(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer id) {

		if (id == null) {
			return null;
		}

		for (TipoCliente x : TipoCliente.values()) {
			if (id.equals(x.getId())) {
				return x;
			}	
		}

		throw new IllegalArgumentException("Invalid Id: " + id);

	}
}
