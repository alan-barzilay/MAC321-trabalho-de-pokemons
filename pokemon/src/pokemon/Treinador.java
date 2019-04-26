package pokemon;

public class Treinador {
	private String nome;
	private int numero_pokemons;
	private int numero_pokemons_vivos;
	//private int numero_pocoes;
	private Pokemon[] pokemons =new Pokemon[6];
	
	private int pokemon_atual;
	
	public Treinador(String nome , Pokemon[] pokemons ) {
		if (pokemons.length>6 || pokemons.length<1) {
			return;
		}
		this.nome =nome;
		//this.numero_pocoes = numero_pocoes;
		this.pokemons = pokemons;
		this.numero_pokemons = pokemons.length;
		this.numero_pokemons_vivos = pokemons.length;	
		this.pokemon_atual = 0;
		
	}

	
	
	
	public int getNPokemonAtual() {
		return pokemon_atual;
	}

	public void setPokemonAtual(int pokemon_atual) {
		this.pokemon_atual = pokemon_atual;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumero_pokemons() {
		return numero_pokemons;
	}

	public void setNumero_pokemons(int numero_pokemons) {
		this.numero_pokemons = numero_pokemons;
	}

	public int getNumero_pokemons_vivos() {
		return numero_pokemons_vivos;
	}

	public void setNumero_pokemons_vivos(int numero_pokemons_vivos) {
		this.numero_pokemons_vivos = numero_pokemons_vivos;
	}

	public Pokemon[] getPokemons() {
		return pokemons;
	}

	public void setPokemons(Pokemon[] pokemons) {
		this.pokemons = pokemons;
	}
	
	public Pokemon getPokemonAtual() {
		return pokemons[ pokemon_atual ];
	}
	

}
