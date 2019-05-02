package pokemon;

import java.util.*;

public class FightController extends Controller {
	private boolean battle = false;
	private int ash = 0, misty = 1;
	Treinador[] treinadores = new Treinador[2];
	
	
	
	public void StartBattle() {
		battle = true;
		
		//Define  os 2 treinadores seus pokemons e ataques
		Pokemon[] pokemons_ash = new Pokemon[3];
		Pokemon[] pokemons_misty = new Pokemon[3];
		
		Ataque[] ataques = {new Ataque("dash", 5), new Ataque("horn",60), new Ataque("growl",1 ), new Ataque("stomp",30)};
		Ataque[] ataques_misty = {new Ataque("splash", 1), new Ataque("splash", 1) ,new Ataque("splash", 1) ,new Ataque("splash", 1)  };
		
		pokemons_ash[0] = new Pokemon("Tauros", 75, ataques );
		pokemons_ash[1] = new Pokemon("Pikachu", 80, ataques );
		pokemons_ash[2] = new Pokemon("Charizard", 105, ataques );
		
		
		pokemons_misty[0] = new Pokemon("Goldeen", 60, ataques_misty );
		pokemons_misty[1] = new Pokemon("Magikarp", 60,ataques_misty);
		pokemons_misty[2] = new Pokemon("Psyduck", 60,ataques_misty);
		
		treinadores[0] = new Treinador("ash" , pokemons_ash);
		treinadores[1] = new Treinador("misty" , pokemons_misty );
	}
	
	private void trocaPokemon(Treinador treinador) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < treinador.getNumero_pokemons(); i++) {
			if (treinador.getPokemons()[i].getHP() > 0)
				set.add(i);
		}
		if (set.contains(treinador.getNPokemonAtual())) {
			set.remove(treinador.getNPokemonAtual()); 
		}
		// escolhe um pokemon aleatoriamente
		int size = set.size();
		int item = new Random().nextInt(size);
		int i = 0;
		for(Object obj : set){

		    if (i == item) {
		    	treinador.setPokemonAtual(i);
		    	System.out.println("Treinador " + treinador.getNome() + " trocou de pokemon , vai " + treinador.getPokemonAtual().getNome() + " !");
		        return ;
		    }
			i++;
		}
		
		
		
	}
	
	private class NovoRound extends Event {
		Treinador treinador;
		public NovoRound(long eventTime, Treinador treinador) {
			super(eventTime);
			this.treinador = treinador;
		}

		public void action() {
			long tm = System.currentTimeMillis();
			double n = Math.random();
				
			// se for o ash
			if (treinadores[0].equals(treinador)) {
				if(n>=0 && n<0.25) {
					//ataca
					addEvent(new Ataca(tm , treinador));
				}
				else if ( n>=0.25 && n<0.5) {
					//trocar pokemon
					addEvent(new Troca(tm , treinador));
				}
				else if (n>=0.5 && n<0.75) {
					// pocao
					addEvent(new Item(tm, treinador));
				}
				else {
					//fugir
					addEvent(new Foge(tm, treinador));
					
				}
			}
			
			// se for a misty
			else {
				addEvent(new Ataca(tm, treinador));
			}
			
			//////////////////	TO DO ////////////////////////////////////////////
			//
			// ver role das prioridades
			//
			// como acabar a luta
			//
			// ver se minha ideia da prioridade quebra a proposta
			//
			// checar se algum deles ainda tem pokemons vivos			
			//////////////////
			
			
			//ash sempre faz o q quiser e dps misty ataca.
			
			//checa se todos pokemons estao mortos, se sim acaba a luta e imprime q acabou
			//se nao, imprime novo estado dos pokemons atuais e  retorna
			
			
			
			//Criar funcoes:
			//checar se todos pokemons  estao mortos e acabar batalha
			//
			
			
		}

		public String description() {
			return " ";
		}
}
	

	private class Item extends Event {
		Treinador treinador;
		public Item(long eventTime, Treinador treinador) {
			super(eventTime);
			this.treinador = treinador;
		}

		public void action() {
			
			treinador.getPokemonAtual().setHP( treinador.getPokemonAtual().getHPMAX());
			
			
			
		}

		public String description() {
			return " ";
		}
}	
	
	
	private class Troca extends Event {
		Treinador treinador;
		public Troca(long eventTime, Treinador treinador) {
			super(eventTime);
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
		public Foge(long eventTime,Treinador treinador) {
			super(eventTime);
			this.treinador = treinador;
		}

		public void action() {
			
			System.out.println("O treinador " + treinador.getNome() + "fugiu da batalha! " );
			
			battle = false;
			
			// terminar e ver coisas das prioridades
		}

		public String description() {
			return " ";
		}
}
	
		
	private class Ataca extends Event {
		Treinador atacante, defensor;
		int i;
		int dano;
		//long eventTime;
		//int w;// controlador do vetor de ataques

		public Ataca(long eventTime, Treinador atacante) {
			super(eventTime);
			
			this.atacante = atacante;
			//this.eventTime = eventTime;
			if (treinadores[0].equals(atacante))
				defensor = treinadores[1];
			else
				defensor = treinadores[0];
			//priority = 0.6;

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
				i =3;
			}
			
			//treinador ataca
			System.out.println( "Treinador " + atacante.getNome() +": " + atacante.getPokemonAtual().getNome() + " ataque com " + atacante.getPokemonAtual().getAtaques()[i].getNome() );
			dano = atacante.getPokemonAtual().getAtaques()[i].getDano();
			System.out.println("O " + defensor.getPokemonAtual().getNome() + " d@ " + defensor.getNome() + " sofreu " + Integer.toString(dano) + " pontos de dano. ");
			defensor.getPokemonAtual().setHP( defensor.getPokemonAtual().getHP() - dano);
			
			
			
			
			//checar se matou pokemon do defensor, se matou e ainda existem pokemons vivos, troca ele
			// se n tiver, n faz nada. Quando sair daqui o numero de pokemons vivos vai ser checado e 
			//a batalha vai acabar
			if (defensor.getPokemonAtual().getHP()<=0 ) {
				System.out.println("Pokemon " + defensor.getPokemonAtual().getNome() + 
						"do treinador"+defensor.getNome() + "morreu");
				defensor.setNumero_pokemons_vivos(defensor.getNumero_pokemons_vivos() - 1);
				if (defensor.getNumero_pokemons_vivos() >0) {
					trocaPokemon(defensor);
					System.out.println("Misty lanca novo pokemon "+ defensor.getPokemonAtual().getNome());
				}
			}
			
		}

		public String description() {
			return " ";
		}
}
	
	
	
	
	public static void main(String[] args) {
		FightController fc = new FightController();
		long tm = System.currentTimeMillis();
		fc.StartBattle();
		
		
		while (fc.battle == true) {
			// acao do ash
			fc.addEvent(fc.new NovoRound(tm, fc.treinadores[0]));
			// faz sentido colocar fc.treinadores?
			
			// acao da misty
			fc.addEvent(fc.new NovoRound(tm, fc.treinadores[1]));
			
			fc.run();
		
		}
		System.out.println("Fim da luta.");


		
		
		
	}
}