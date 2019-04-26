package pokemon;

public class FightController extends Controller {
	private boolean battle = false;
	private int ash = 0, misty = 1;
	Treinador[] treinadores = new Treinador[2];
	
	
	
	public void StartBattle() {
		battle = true;
		
		
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
	
	
	
	private class NovoRound extends Event {
		public NovoRound(long eventTime) {
			super(eventTime);
		}

		public void action() {
			long tm = System.currentTimeMillis();
			double n = Math.random();
			
			if(n>=0 && n<0.25) {
				addEvent(new Ataca(tm));
			}
			else if ( n>=0.25 && n<0.5) {
				//trocar pokemon
			}
			else if (n>=0.5 && n<0.75) {
				// pocao
			}
			else {
				//fugir
			}
			
			
			//ash sempre faz o q quiser e dps misty ataca.
			
			//checa se todos pokemons estao mortos, se sim acaba a luta e imprime q acabou
			//se nao, imprime novo estado dos pokemons atuais e  retorna
			
			
			//funcao atacar sempre checar se matou pokemon ou n pra ja trocar.
			
			//Criar funcoes:
			//checar se pokemon atual ta vivo e trocar ativo
			//checar se todos pokemons  estao mortos e acabar batalha
			//
			//criar funcao da misty atacando o ash
			//
			//checar qual eh o pokemon atual
			//
			
			
		}

		public String description() {
			return " ";
		}
}
	

	private class Item extends Event {
		public Item(long eventTime) {
			super(eventTime);
		}

		public void action() {
			
			
			
		}

		public String description() {
			return " ";
		}
}	
	
	
	private class Troca extends Event {
		public Troca(long eventTime) {
			super(eventTime);
		}

		public void action() {
			
			// ver quais pokemons tao vivos
			// trocar por outro pokemon q esteja vivo
			
			
			
		}

		public String description() {
			return " ";
		}
}
	
	
	
	private class Ataca extends Event {
		int i;
		int dano;
		public Ataca(long eventTime) {
			super(eventTime);
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
			// se der tempo, transformar isso numa funcao pra dar uma limpada nessa parte do codigo
			
			//ash ataca
			dano = treinadores[0].getPokemonAtual().getAtaques()[i].getDano();
			treinadores[1].getPokemonAtual().setHP( treinadores[1].getPokemonAtual().getHP() - dano);
			
			//checar se matou pokemon da misty
			if (treinadores[1].getPokemonAtual().getHP()<=0 ) {
				System.out.println("Pokemon " + treinadores[1].getPokemonAtual().getNome() + 
						"do treinador"+treinadores[1].getNome() + "morreu");
				treinadores[1].setNumero_pokemons_vivos(treinadores[1].getNumero_pokemons_vivos() - 1);
				if (treinadores[1].getNumero_pokemons_vivos() >0) {
					treinadores[1].setPokemonAtual(treinadores[1].getNPokemonAtual() +1);
					System.out.println("Misty lanca novo pokemon "+ treinadores[1].getPokemonAtual().getNome());
				}
			}
			//misty ataca
			dano = treinadores[1].getPokemonAtual().getAtaques()[i].getDano();
			treinadores[0].getPokemonAtual().setHP( treinadores[0].getPokemonAtual().getHP() - dano);
			
			//checar se matou pokemon do ash
			if (treinadores[0].getPokemonAtual().getHP()<=0 ) {
				System.out.println("Pokemon " + treinadores[0].getPokemonAtual().getNome() + 
						"do treinador"+treinadores[0].getNome() + "morreu");
				treinadores[0].setNumero_pokemons_vivos(treinadores[1].getNumero_pokemons_vivos() - 1);
				if (treinadores[0].getNumero_pokemons_vivos() >0) {
					treinadores[0].setPokemonAtual(treinadores[0].getNPokemonAtual() +1);
					System.out.println("Ash lanca novo pokemon "+ treinadores[0].getPokemonAtual().getNome());
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
			fc.addEvent(fc.new NovoRound(tm));
			fc.run();
		
		}


		
		
		
	}
}