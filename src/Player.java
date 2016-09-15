
public class Player {
	private String id;
	private String nome;
	private PlayerKD kd;
	
	@Override
	public String toString() {
		return "nome: " + this.nome + ", playerKD: " + this.kd;
	}
	
	public Player (String id, String nome, PlayerKD kd) {
		this.id = id;
		this.nome = nome;
		this.kd = kd;
	}
	
	public String getNome () {
		return nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public PlayerKD getPlayerKD () {
		return kd;
	}
	
	public void setPlayerKD (PlayerKD kd) {
		this.kd = kd;
	}
	
	public String getID () {
		return id;
	}
	
	public void setID (String id) {
		this.id = id;
	}
}
