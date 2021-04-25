package dados;

public class Donations {
	
	private String tipo;
	private String estado;
	private String descricao;
	private String pontos;
	
	public Donations(String tipo, String estado, String descricao) {
		this.tipo = tipo;
		this.estado = estado;
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

	public String getTipo(){
		return this.tipo;
	}

	public String getEstado(){
		return this.estado;
	}

	public String getPontos(){
		return this.pontos;
	}

}
