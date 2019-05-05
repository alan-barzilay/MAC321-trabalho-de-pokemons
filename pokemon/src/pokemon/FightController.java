package pokemon;

import java.util.*;

public class FightController extends Controller {
	private boolean battle = false;
	Treinador[] treinadores = new Treinador[2];
	
//		TODO
//	
//	clear events n ta apagando direito, misty atacou com pokemon ja morto (era o ultimo dela)
//	
//	quando um pokemon morre, o pokemon seguinte ainda ataca. A questao eh q quando todos estao mortos ainda rola isso e o pokemon ativo eh o ultimo a morrer
//	
//	
//	
//	
//	
//	
//	Treinador ash: Pikachu ataque com cauda de ferro 
//	O Magikarp d@ misty sofreu 60 pontos de dano. 
//	Pokemon Magikarp do treinador misty morreu.
//
//	Treinador misty: Magikarp ataque com splash 
//	O Pikachu d@ ash sofreu 1 pontos de dano. 
//
//	-----------------
//	ash
//	Pikachu , HP: 77
//	-----------------
//	misty
//	Magikarp , HP: 0
//	-----------------
//	Todos os pokemons do treinador  misty morreram. Fim da luta!
////////////////////////////////////////////////////
//	
//	
//	Ordem de prioridades nem sempre ta sendo respeitado, as vezes o segundo treinador ataca antes
//	
//	
//	
//	
//	
//	
//	
//	

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
		
		System.out.println("-----------------");
		System.out.println(treinador1.getNome());
		System.out.println(treinador1.getPokemonAtual().getNome() + " , HP: "+ treinador1.getPokemonAtual().getHP());
		System.out.println("-----------------");
		System.out.println(treinador2.getNome());
		System.out.println(treinador2.getPokemonAtual().getNome() + " , HP: "+ treinador2.getPokemonAtual().getHP());
		System.out.println("-----------------");
	}


	
	
	
	
	private void checaSobreviventes() {
		//checa se algum dos 2 treinadores ta sem pokemons vivos
		checaSobrevivente(treinadores[0]);
		checaSobrevivente(treinadores[1]);		
	}
	
	private void checaSobrevivente(Treinador treinador){
		if (treinador.getNumero_pokemons_vivos() == 0) {
			System.out.println("Todos os pokemons do treinador  " + treinador.getNome() + " morreram. Fim da luta!");
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
		
		treinadores[0] = new Treinador("ash" , pokemons_ash);
		treinadores[1] = new Treinador("wild" , pokemon_selvagem );
	}
	
	
	
	public void comecaLutaTreinador() {
		battle = true;
		
		//Define  os 2 treinadores seus pokemons e ataques
		Pokemon[] pokemons_ash = new Pokemon[3];
		Pokemon[] pokemons_misty = new Pokemon[3];;
		
		
		Ataque[] ataques_misty = {new Ataque("splash", 1), new Ataque("splash", 1) ,new Ataque("splash", 1) ,new Ataque("splash", 1)  };
		
		Ataque[] ataques_tauros = {new Ataque("dash", 10), new Ataque("horn",60), new Ataque("growl",1 ), new Ataque("stomp",30)};
		Ataque[] ataques_charizard = {new Ataque("hiper beam", 120), new Ataque("dragon rage",60), new Ataque("lanca chamas",55 ), new Ataque("ember",30)};
		Ataque[] ataques_pikachu = {new Ataque("choque do trovao", 100), new Ataque("cauda de ferro",60), new Ataque("raio de trovao",10 ), new Ataque("onda relampago",50)};
		
		pokemons_ash[1] = new Pokemon("Tauros", 75, ataques_tauros );
		pokemons_ash[0] = new Pokemon("Pikachu", 80, ataques_pikachu );
		pokemons_ash[2] = new Pokemon("Charizard", 105, ataques_charizard );
		
		
		pokemons_misty[0] = new Pokemon("Goldeen", 60, ataques_misty );
		pokemons_misty[1] = new Pokemon("Magikarp", 60,ataques_misty);
		pokemons_misty[2] = new Pokemon("Psyduck", 60,ataques_misty);
		
		treinadores[0] = new Treinador("ash" , pokemons_ash);
		treinadores[1] = new Treinador("misty" , pokemons_misty );
	}
	
	private void trocaPokemon(Treinador treinador, long eventTime) {
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
			// nao vale a pena o trabalho para dar uma segunda chance ao treinador
			//addEvent(new NovoRound( eventTime , treinador));
			return;
		}
		
		// escolhe um pokemon aleatoriamente
		int n = new Random().nextInt(size);
		Integer[] array = set.toArray(new Integer[set.size()] );
		treinador.setPokemonAtual(array[n]);
    	System.out.println("Treinador " + treinador.getNome() + " trocou de pokemon , vai " + treinador.getPokemonAtual().getNome() + " ! ");

//		
//		int i = 0;
//		for(Object obj : set){
//
//		    if (i == n) {
//		    	treinador.setPokemonAtual(i);
//		    	System.out.println("Treinador " + treinador.getNome() + " trocou de pokemon , vai " + treinador.getPokemonAtual().getNome() + " ! ");
//		        return ;
//		    }
//			i++;
//		}
//		
		
	}
	
	private class NovoRound extends Event {
		Treinador treinador;
		public NovoRound(long eventTime, Treinador treinador) {
			super(eventTime);
			this.treinador = treinador;
		}

		public void action() {
			long tm = getEvtTime();
			double n = Math.random();
				
			// se for o ash
			if (treinadores[0].equals(treinador)) {
				if(n>=0 && n<0.7) {
					//ataca
					addEvent(new Ataca(tm+1000, treinador));
				}
				else if ( n>=0.7 && n<0.8) {
					// pocao
					addEvent(new Item(tm+750, treinador));
					
				}
				else if (n>=0.8 && n<0.95) {
					//trocar pokemon
					addEvent(new Troca(tm+500, treinador));
					
				}
				else {
					//fugir
					addEvent(new Foge(tm+0, treinador));
					
				}
			}
			
			// se for a misty
			else {
				addEvent(new Ataca(tm + 1000, treinador));
			}
			
			checaSobreviventes();			
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
			return "Treinador " +treinador.getNome() + " usou uma pocao! " + treinador.getPokemonAtual().getNome() + " recuperou totalmente seu HP.";
		}
}	
	
	
	private class Troca extends Event {
		Treinador treinador;
		long eventTime;
		public Troca(long eventTime, Treinador treinador) {
			super(eventTime);
			this.treinador =treinador;
			this.eventTime = eventTime;
			
		}

		public void action() {
			trocaPokemon(treinador, eventTime);			
			
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
			encerraLuta();
		
		}

		public String description() {
			return "O treinador " + treinador.getNome() + "fugiu da batalha! " ;
		}
}
	
		
	private class Ataca extends Event {
		Treinador atacante, defensor;
		int i;
		int dano;
		long eventTime;
		//int w;// controlador do vetor de ataques

		public Ataca(long eventTime, Treinador atacante) {
			super(eventTime);
			
			this.atacante = atacante;
			this.eventTime = eventTime;
			
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
				i =3;
			}
			
			//treinador ataca
			
			dano = atacante.getPokemonAtual().getAtaques()[i].getDano();
			defensor.getPokemonAtual().setHP( defensor.getPokemonAtual().getHP() - dano);
			
			String texto = "";
			texto += "Treinador " + atacante.getNome() +": " + atacante.getPokemonAtual().getNome() + " ataque com " + atacante.getPokemonAtual().getAtaques()[i].getNome()  ;
			texto += " \n";
			texto += "O " + defensor.getPokemonAtual().getNome() + " d@ " + defensor.getNome() + " sofreu " + Integer.toString(dano) + " pontos de dano. " ;
			
			System.out.println(texto);
			
			//checar se matou pokemon do defensor, se matou e ainda existem pokemons vivos, troca ele
			// se n tiver, n faz nada. Quando sair daqui o numero de pokemons vivos vai ser checado e 
			//a batalha vai acabar
			if (defensor.getPokemonAtual().getHP()<=0 ) {
				System.out.println("Pokemon " + defensor.getPokemonAtual().getNome() + " do treinador "+defensor.getNome() + " morreu.");
				
				defensor.setNumero_pokemons_vivos(defensor.getNumero_pokemons_vivos() - 1);
				if (defensor.getNumero_pokemons_vivos() >0) {
					
					trocaPokemon(defensor, eventTime);
					//texto+= defensor.getNome() +  " lanca novo pokemon "+ defensor.getPokemonAtual().getNome();
				}
			}		
		}

		public String description() {
			
			
			return "";
		}
}
	
	
	public void adicionaRound(long tm, Treinador treinador) {
		addEvent(new NovoRound(tm , treinador));	
	}
	
	
	public static void main(String[] args) {
		FightController fc = new FightController();
		long tm = System.currentTimeMillis();
		fc.comecaLutaTreinador();
		
		
		System.out.println("                                   ,'\\\n" + 
				"    _.----.        ____         ,'  _\\   ___    ___     ____\n" + 
				"_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n" + 
				"\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n" + 
				" \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |\n" + 
				"   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n" + 
				"    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |\n" + 
				"     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n" + 
				"      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n" + 
				"       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n" + 
				"        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n" + 
				"                                `'                            '-._|");
		
		
		
		
		while (fc.isBattle() == true) {
			tm = System.currentTimeMillis();
			
			// acao do ash
			fc.statusTreinadores();
			fc.adicionaRound(tm, fc.treinadores[0]);
			
			// acao da misty
			fc.adicionaRound(tm+100, fc.treinadores[1]);
			
			fc.run();
		
		}
		System.out.println("Fim da luta.");

		System.out.println("   .\"-,.__\n" + 
				"                 `.     `.  ,\n" + 
				"              .--'  .._,'\"-' `.\n" + 
				"             .    .'         `'\n" + 
				"             `.   /          ,'\n" + 
				"               `  '--.   ,-\"'\n" + 
				"                `\"`   |  \\\n" + 
				"                   -. \\, |\n" + 
				"                    `--Y.'      ___.\n" + 
				"                         \\     L._, \\\n" + 
				"               _.,        `.   <  <\\                _\n" + 
				"             ,' '           `, `.   | \\            ( `\n" + 
				"          ../, `.            `  |    .\\`.           \\ \\_\n" + 
				"         ,' ,..  .           _.,'    ||\\l            )  '\".\n" + 
				"        , ,'   \\           ,'.-.`-._,'  |           .  _._`.\n" + 
				"      ,' /      \\ \\        `' ' `--/   | \\          / /   ..\\\n" + 
				"    .'  /        \\ .         |\\__ - _ ,'` `        / /     `.`.\n" + 
				"    |  '          ..         `-...-\"  |  `-'      / /        . `.\n" + 
				"    | /           |L__           |    |          / /          `. `.\n" + 
				"   , /            .   .          |    |         / /             ` `\n" + 
				"  / /          ,. ,`._ `-_       |    |  _   ,-' /               ` \\\n" + 
				" / .           \\\"`_/. `-_ \\_,.  ,'    +-' `-'  _,        ..,-.    \\`.\n" + 
				".  '         .-f    ,'   `    '.       \\__.---'     _   .'   '     \\ \\\n" + 
				"' /          `.'    l     .' /          \\..      ,_|/   `.  ,'`     L`\n" + 
				"|'      _.-\"\"` `.    \\ _,'  `            \\ `.___`.'\"`-.  , |   |    | \\\n" + 
				"||    ,'      `. `.   '       _,...._        `  |    `/ '  |   '     .|\n" + 
				"||  ,'          `. ;.,.---' ,'       `.   `.. `-'  .-' /_ .'    ;_   ||\n" + 
				"|| '              V      / /           `   | `   ,'   ,' '.    !  `. ||\n" + 
				"||/            _,-------7 '              . |  `-'    l         /    `||\n" + 
				". |          ,' .-   ,' ||               | .-.        `.      .'     ||\n" + 
				" `'        ,'    `\".'    |               |    `.        '. -.'       `'\n" + 
				"          /      ,'      |               |,'    \\-.._,.'/'\n" + 
				"          .     /        .               .       \\    .''\n" + 
				"        .`.    |         `.             /         :_,'.'\n" + 
				"          \\ `...\\   _     ,'-.        .'         /_.-'\n" + 
				"           `-.__ `,  `'   .  _.>----''.  _  __  /\n" + 
				"                .'        /\"'          |  \"'   '_\n" + 
				"               /_|.-'\\ ,\".             '.'`__'-( \\\n" + 
				"                 / ,\"'\"\\,'               `/  `-.|\"  ");

		
		
		
	}
}