package pokemon;

public class Pokemon {
	private String nome;
	private int HP;
	private int HPMAX;
	//private String type;
	
	private Ataque[] ataques = new Ataque[4];
	
	public Pokemon(String nome, int HP , Ataque[] ataques) {
		this.nome = nome;
		this.HP = HP;
		this.ataques = ataques;
		this.HPMAX = HP;
	}
	
	
	
	public int getHPMAX() {
		return HPMAX;
	}

	public void setHPMAX(int hPMAX) {
		HPMAX = hPMAX;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		if (hP<0) {
			hP =0;
		}
		HP = hP;
	}

	public Ataque[] getAtaques() {
		return ataques;
	}

	public void setAtaques(Ataque[] ataques) {
		this.ataques = ataques;
	}
	
	
	

}
