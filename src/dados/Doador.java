package dados;

public class Doador implements Comparable<Doador>{

	private String nome;
	private int pontos;
	
	public Doador(String nome, int pontos) {
		this.nome = nome;
		this.pontos = pontos;
	}
	
	public String getNome() {
		return this.nome;
	}
	public int getPontos() {
		return this.pontos;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public String toString() {
		return this.nome;
	}

	@Override
	public int compareTo(Doador o) {
		Doador aux = (Doador) o;
		if(this.pontos > aux.getPontos()) {
			return -1;
		}
		if(this.pontos < aux.getPontos()) {
			return 1;
		}
		return 0;
	}
}
