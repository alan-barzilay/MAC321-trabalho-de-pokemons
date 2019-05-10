package pokemon;

public class AndarController extends Controller {
	boolean chegouNoFim = true;
	int posicaoNoMapa;
	// 0 eh chao comum e 1 eh grama
	int[] mapa = {0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0} ;
	

	public void encontraPokemon(double priority) {		
		double n = Math.random();
		if ( n < 0.45) {
			//encontrou pokemon
			comecaBatalha();	
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
		System.out.println("]");		
	}

	private class AndaUmPasso extends Event{
		
		public AndaUmPasso(double priority) {
			super(priority);
		}
		
		public void action(){
			if (posicaoNoMapa < mapa.length -1) {
				posicaoNoMapa +=1;
				if (mapa[posicaoNoMapa] == 1 ) {
					encontraPokemon( getPriority() );
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
	
	private void comecaBatalha(){
		System.out.println("\n\nTreinador encontrou um pokemon selvagem!");		
		FightController fc = new FightController();
		fc.comecaLutaSelvagem();
		while (fc.isBattle() == true) {
			//acao do ash
			fc.statusTreinadores();
			fc.novoRound(0.1, fc.treinadores[0]);
			// acao pokemon selvagem
			fc.novoRound(0.0, fc.treinadores[1]);
			
			fc.run();	
			fc.checaSobreviventes();
		}			
		System.out.println("Fim da luta");
		
		
	}
	
	private void comecaJornada() {
		imprimeLogo();
		chegouNoFim = false;
		posicaoNoMapa = 0;	
		
		System.out.println("Legenda:");
		System.out.println("P         => Posicao do Jogador");
		System.out.println("_________ => Chao Comum");
		System.out.println(",,,,,,,,, => Grama Alta");
		System.out.println(" \n\n\n\n O treinador comecara sua jornada pela Victory Road!");
		imprimeMapa();
	}
	
	
	
	public static void main(String[] args) {
		AndarController ac = new AndarController();
		ac.comecaJornada();

		while( ac.chegouNoFim == false) {
			
			ac.addEvent(ac.new AndaUmPasso(0.0)); 
			ac.run();
		}
		
		System.out.println(" \n---------------------------------------------------------------------------------------\n O treinador atravessou a Victory Road e esta pronto para desafiar a elite 4!\n---------------------------------------------------------------------------------------");
		imprimeLapras();
	}

}
