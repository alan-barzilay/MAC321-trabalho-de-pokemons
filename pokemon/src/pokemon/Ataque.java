package pokemon;

public class Ataque {
	private String nome;
	private int dano;
	
	public Ataque(String nome, int dano) {
		this.nome =nome;
		this.dano = dano;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}
	
	
	
	
}
