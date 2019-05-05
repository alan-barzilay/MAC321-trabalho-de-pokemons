package pokemon;

//import pokemon.FightController.NovoRound;

public class AndarController extends Controller {
	boolean chegouNoFim = true;
	int posicaoNoMapa;
	// 0 eh chao comum e 1 eh grama
	int[] mapa = {0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0} ;
	

	public void encontraPokemon(long tm) {		
		double n = Math.random();
		if ( n < 0.45) {
			//encontrou pokemon
			addEvent(new ComecaBatalha(tm) ) ;	
		}		
	}
	
	public void imprimeMapa() {
		System.out.print("[");
		for(int i =0 ; i< mapa.length ; i++) {			
			if( mapa[i] == 0) {
				System.out.print("___");
				if ( i==posicaoNoMapa) {
					System.out.print("P");
				}
				System.out.print("___");
			}
			else if (mapa[i] == 1) {
				System.out.print(",,,,");
				if ( i==posicaoNoMapa) {
					System.out.print("P");
				}
				System.out.print(",,,,");	
			}		
		}
		System.out.print("]");		
	}
	
	public void andaJogador() {
		
	}
	
	private class AndaUmPasso extends Event{
		
		public AndaUmPasso(long eventTime) {
			super(eventTime);
		}
		
		public void action(){
			if (posicaoNoMapa < mapa.length -1) {
				posicaoNoMapa +=1;
				if (mapa[posicaoNoMapa] == 1 ) {
					encontraPokemon( getEvtTime() );
				}
				imprimeMapa();
			}
			else {
				// Acaba jornada
				chegouNoFim = true;
			}
		}
		
		public String description() {
			return "";
		}
		
		
	}
	
	private class ComecaBatalha extends Event{
		
		public ComecaBatalha(long eventTime) {
			super(eventTime);
		}
		
		public void action(){
			System.out.println("\n\nTreinador encontrou um pokemon selvagem!");		
			FightController fc = new FightController();
			long tm = System.currentTimeMillis();
			fc.comecaLutaSelvagem();
			
			
			while (fc.isBattle() == true) {
				//acao do ash
				fc.statusTreinadores();
				fc.adicionaRound(tm, fc.treinadores[0]);
				
				//acao pokemon selvagem
				fc.adicionaRound(tm+300, fc.treinadores[1]);
				
				fc.run();		
			}			
		}
		
		public String description() {
			return "Fim da Luta.\n\n";
		}
	}
	
	private void comecaJornada() {
		chegouNoFim = false;
		posicaoNoMapa = 0;		
	}
	
	
	
	public static void main(String[] args) {
		AndarController ac = new AndarController();
		long tm = System.currentTimeMillis();
		ac.comecaJornada();
		
		
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
		
		System.out.println("Legenda:");
		System.out.println("P         => Posicao do Jogador");
		System.out.println("_________ => Chao Comum");
		System.out.println(",,,,,,,,, => Grama Alta");
		System.out.println(" \n\n\n\n O treinador comecara sua jornada pela Victory Road!");
		ac.imprimeMapa();
		System.out.println();
		
		while( ac.chegouNoFim == false) {
			tm = System.currentTimeMillis();
			ac.addEvent(ac.new AndaUmPasso(tm + 200)); 
			ac.run();
		}
		
		System.out.println(" \n---------------------------------------------------------------------------------------\n O treinador atravessou a Victory Road e esta pronto para desafiar a elite 4!\n---------------------------------------------------------------------------------------");
		System.out.println("                                       ,|\n" + 
				"                                       ||\n" + 
				"                               ,-\"'\"\"`' `._\n" + 
				"                              '----.     __`....._\n" + 
				"                               `    `.  `. ;      `.\n" + 
				"           FIM                  `.    `.  `   ,\"`. |\n" + 
				"                                  `.  _.`._   |  ' |\n" + 
				"                                  .','  ,' `.  `--'\n" + 
				"                                 /.' _,'    | /\n" + 
				"                                '/_.'       |.\n" + 
				"                                 `---`\".    ||\n" + 
				"                                       |    ||\n" + 
				"                                      ,'    `|\n" + 
				"                         _           /       |\n" + 
				"                        ' `.        .'       |\n" + 
				"                         .  `._  _,'/|       |\n" + 
				"                        _|     \"'  / |       '\n" + 
				"                    _,-' |        /  '        .\n" + 
				"                 |\"'            ,'  '          \\\n" + 
				"                 |   _        ,'   /            \\\n" + 
				"                 ;  '        /    j              .\n" + 
				"            ,\"--'    `.    .      |              |         ________\n" + 
				"            `.   -.       / '     |              |   _,-\"\"'   __.._\"`-._\n" + 
				"             ,' ,-.`-.__.' /      '              |.-'  _..--'\"       _.-'\n" + 
				"             \\.'   `-.___.'      ,               '__.-\"           _.'\n" + 
				"             /        _..--    . |              /               ,'\n" + 
				"           ,`      .-'         | |           _,'._          _,-'\n" + 
				"       _,-'      ,'           .' '       _.-'     \"-.....-\"'\n" + 
				"     ,'     __ ,'          _.'  /  __..-'\n" + 
				"   ,' _.-\"\"'  /         _.'  _.'-\"'\n" + 
				"  '-'\"       /      _.-' _.-\"\n" + 
				"            /    _.' _.-'\n" + 
				"           .   .'_.-'\n" + 
				"           | ,'.'\n" + 
				"           | .`\n" + 
				"            `");
		
		
		// anda um passo
		//checa se encontrou pokemon
		// se encontrou, comeca batalha e acaba ela antes de dar proximo passo
		// vai dando passos ate acabar passos pra dar
		
		// treinador vai poder dar passos numa linah reta, comecando na origem
		// se ele ta na origem ou no fim, o proximo passo dele ta determinado
		
		
		//[____P__________________________________________,,,,,,,,,,,,,,,,,,__________]         
		//[__________________P____________________________,,,,,,,,,,,,,,,,,,__________]  
		//[_______________________________________________,,,,,,,,,P,,,,,,,,__________]  
		//[___________________________________P __________,,,,,,,,,,,,,,,,,,__________]  
				
		
		//                ",,,,P,,,,"    Jogador na grama 
		//                "____P____"	   Jogador no chao comum
		
		
	
		/////////
		// sepa q isso eh inutil:
		/////////
		//andar passo eventTime + 100
		//comecar batalha eventTime + 0
		
		
	}

}
