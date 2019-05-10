package pokemon;

import java.util.*;

public class FightController extends Controller {
	private boolean battle = false;
	Treinador[] treinadores = new Treinador[2];
	

	public boolean isBattle() {
		return battle;
	}
	public void setBattle(boolean battle) {
		this.battle = battle;
	}
	public Treinador[] getTreinadores() {
		return treinadores;
	}
	public void setTreinadores(Treinador[] treinadores) {
		this.treinadores = treinadores;
	}

	public void statusTreinadores() {
		Treinador treinador1 = treinadores[0]; 
		Treinador treinador2 = treinadores[1];
		
		System.out.println("\n-----------------");
		System.out.println(treinador1.getNome());
		System.out.println(treinador1.getPokemonAtual().getNome() + " , HP: "+ treinador1.getPokemonAtual().getHP());
		System.out.println("-----------------");
		System.out.println(treinador2.getNome());
		System.out.println(treinador2.getPokemonAtual().getNome() + " , HP: "+ treinador2.getPokemonAtual().getHP());
		System.out.println("-----------------\n");
	}


	
	
	
	
	public void checaSobreviventes() {
		//checa se algum dos 2 treinadores ta sem pokemons vivos
		checaSobrevivente(treinadores[0]);
		checaSobrevivente(treinadores[1]);		
		
	}
	
	private void checaSobrevivente(Treinador treinador){
		if (treinador.getNumero_pokemons_vivos() == 0) {
			System.out.println("Todos os pokemons  " + treinador.getNome() + " morreram. ");
			encerraLuta();
			
		}		
	}
	
	private void encerraLuta() {
		// acaba a batalha e apaga todos os eventos 
		//(importante pra caso ainda tenha  a ação do segundo treinador ja na lista para ocorrer mas precisamos acabar a luta antes disso)
		battle = false;
		clearEvents();	
	}
	
	public void comecaLutaSelvagem() {
		battle = true;
		
		//Define  os 2 treinadores seus pokemons e ataques
		Pokemon[] pokemons_ash = new Pokemon[3];
		Pokemon[] pokemon_selvagem = new Pokemon[1];
		
		Ataque[] ataques_tauros = {new Ataque("dash", 10), new Ataque("horn",60), new Ataque("growl",1 ), new Ataque("stomp",30)};
		Ataque[] ataques_charizard = {new Ataque("hiper beam", 120), new Ataque("dragon rage",60), new Ataque("lanca chamas",55 ), new Ataque("ember",30)};
		Ataque[] ataques_pikachu = {new Ataque("choque do trovao", 100), new Ataque("cauda de ferro",60), new Ataque("raio de trovao",10 ), new Ataque("onda relampago",50)};
		
		pokemons_ash[0] = new Pokemon("Tauros", 75, ataques_tauros );
		pokemons_ash[1] = new Pokemon("Pikachu", 80, ataques_pikachu );
		pokemons_ash[2] = new Pokemon("Charizard", 105, ataques_charizard );
		

		Ataque[] ataques_ratata = {new Ataque("growl", 1), new Ataque("growl", 1) ,new Ataque("headbutt", 10) ,new Ataque("growl", 1)  };
		
		
		pokemon_selvagem[0] = new Pokemon("Ratata", 60, ataques_ratata );
		
		treinadores[0] = new Treinador("Ash" , pokemons_ash);
		treinadores[1] = new Treinador("Selvagem" , pokemon_selvagem );
	}
	
	
	
	public void comecaLutaTreinador() {
		imprimeLogo();
		battle = true;
		
		//Define  os 2 treinadores seus pokemons e ataques
		Pokemon[] pokemons_ash = new Pokemon[3];
		Pokemon[] pokemons_garry = new Pokemon[3];;
		
		
		Ataque[] ataques_garry = {new Ataque("splash", 1), new Ataque("splash", 1) ,new Ataque("splash", 1) ,new Ataque("splash", 1)  };
		
		Ataque[] ataques_tauros = {new Ataque("dash", 10), new Ataque("horn",60), new Ataque("growl",1 ), new Ataque("stomp",30)};
		Ataque[] ataques_charizard = {new Ataque("hiper beam", 120), new Ataque("dragon rage",60), new Ataque("lanca chamas",55 ), new Ataque("ember",30)};
		Ataque[] ataques_pikachu = {new Ataque("choque do trovao", 100), new Ataque("cauda de ferro",60), new Ataque("raio de trovao",10 ), new Ataque("onda relampago",50)};
		
		pokemons_ash[1] = new Pokemon("Tauros", 75, ataques_tauros );
		pokemons_ash[0] = new Pokemon("Pikachu", 80, ataques_pikachu );
		pokemons_ash[2] = new Pokemon("Charizard", 105, ataques_charizard );
		
		
		pokemons_garry[0] = new Pokemon("Goldeen", 60, ataques_garry );
		pokemons_garry[1] = new Pokemon("Magikarp", 60,ataques_garry);
		pokemons_garry[2] = new Pokemon("Psyduck", 60,ataques_garry);
		
		treinadores[0] = new Treinador("Ash" , pokemons_ash);
		treinadores[1] = new Treinador("Garry" , pokemons_garry );
	}
	
	private void trocaPokemon(Treinador treinador) {
		// cria set de pokemons vivos
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < treinador.getNumero_pokemons(); i++) {
			if (treinador.getPokemons()[i].getHP() > 0)
				set.add(i);
		}
		// tire do set o pokemon atual 
		if (set.contains(treinador.getNPokemonAtual())) {
			set.remove(treinador.getNPokemonAtual()); 
		}
		

		int size = set.size();
		
		//checa se tem outro pokemon pra trocar
		
		if (size ==0) {
			// ja q n tem outro pokemon, avisa q n tem e acaba a rodada.
			System.out.println(" Treinador " + treinador.getNome() + " tentou trocar de pokemon mas não tem outros pokemons para escolher! " );
			return;
		}
		
		// escolhe um pokemon aleatoriamente
		int n = new Random().nextInt(size);
		Integer[] array = set.toArray(new Integer[set.size()] );
		treinador.setPokemonAtual(array[n]);
    	System.out.println("Treinador " + treinador.getNome() + " trocou de pokemon , vai " + treinador.getPokemonAtual().getNome() + " ! ");
	}
	
	public void novoRound(double priority , Treinador treinador) {			
		double n = Math.random();
			
		// se for o ash
		if (treinadores[0].equals(treinador)) {
			if(n>=0 && n<0.7) {
				//ataca
				addEvent(new Ataca(priority, treinador));
			}
			else if ( n>=0.7 && n<0.8) {
				// pocao
				addEvent(new Item(priority, treinador));
				
			}
			else if (n>=0.8 && n<0.95) {
				//trocar pokemon
				addEvent(new Troca(priority, treinador));
				
			}
			else {
				//fugir
				addEvent(new Foge(priority, treinador));				
			}
		}
		
		// se for a garry
		else {
			addEvent(new Ataca(priority, treinador));
		}	
					
}
	

	private class Item extends Event {
		Treinador treinador;
		public Item(double priority, Treinador treinador) {
			super(priority+0.4);
			this.treinador = treinador;
		}

		public void action() {
			
			treinador.getPokemonAtual().setHP( treinador.getPokemonAtual().getHPMAX());
			
			
			
		}

		public String description() {
			return "Treinador " +treinador.getNome() + " usou uma pocao! \n" + treinador.getPokemonAtual().getNome() + " recuperou totalmente seu HP.\n";
		}
}	
	
	
	private class Troca extends Event {
		Treinador treinador;
		
		public Troca(double priority, Treinador treinador) {
			super(priority+0.6);
			this.treinador =treinador;			
		}

		public void action() {
			trocaPokemon(treinador);			
			
		}

		public String description() {
			return " ";
		}
}
		
	
	private class Foge extends Event {
		Treinador treinador;
		public Foge(double priority,Treinador treinador) {
			super(priority+0.8);
			this.treinador = treinador;
		}

		public void action() {
			System.out.println("O treinador " + treinador.getNome() + " fugiu da batalha! ");
			encerraLuta();
			
		
		}

		public String description() {
			return "";
		}
}
	
		
	private class Ataca extends Event {
		Treinador atacante, defensor;
		int i;
		int dano;
	
		public Ataca(double priority, Treinador atacante) {
			super(priority+0.2);			
			this.atacante = atacante;
			
			if (treinadores[0].equals(atacante))
				defensor = treinadores[1];
			else
				defensor = treinadores[0];
		}

		public void action() {
			double n = Math.random();
			if(n>=0 && n<0.25) {
				i =0;
			}
			else if ( n>=0.25 && n<0.5) {
				i = 1;
			}
			else if (n>=0.5 && n<0.75) {
				i = 2;
			}
			else {
				i = 3;
			}
			
			//treinador ataca
			
			dano = atacante.getPokemonAtual().getAtaques()[i].getDano();
			defensor.getPokemonAtual().setHP( defensor.getPokemonAtual().getHP() - dano);
			
			String texto = "";
			texto +=  atacante.getNome() +" " + atacante.getPokemonAtual().getNome() + " ataca com " + atacante.getPokemonAtual().getAtaques()[i].getNome()  ;
			texto += " \n";
			texto +=   defensor.getNome()+ "  " + defensor.getPokemonAtual().getNome()  +" sofreu " + Integer.toString(dano) + " pontos de dano. " ;
			
			System.out.println(texto);
			
			//checar se matou pokemon do defensor, se matou e ainda existem pokemons vivos, troca ele
			// se n tiver, n faz nada. Quando sair daqui o numero de pokemons vivos vai ser checado e 
			//a batalha vai acabar
			if (defensor.getPokemonAtual().getHP()<=0 ) {
				System.out.println("Pokemon " + defensor.getPokemonAtual().getNome() + "  "+defensor.getNome() + " morreu.");	
				defensor.setNumero_pokemons_vivos(defensor.getNumero_pokemons_vivos() - 1);
				clearEvents();
				if (defensor.getNumero_pokemons_vivos() >0) {				
					trocaPokemon(defensor);
					
				}
			}		
		}

		public String description() {
			
			
			return "";
		}
}
	
		
	
	
	public static void main(String[] args) {
		FightController fc = new FightController();
		fc.comecaLutaTreinador();
		

		while (fc.isBattle() == true) {
			// acao do ash
			fc.statusTreinadores();
			fc.novoRound(0.1, fc.treinadores[0]);
			// acao da garry
			fc.novoRound(0.0, fc.treinadores[1]);
			
			fc.run();
			fc.checaSobreviventes();
		
		}
		System.out.println("Fim da luta.");
		imprimeCharizard();	
	}
}