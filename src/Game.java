import java.util.List;

public class Game {
	private String nome;
	private List<Player> players;
	
	public Game (String nome, List<Player> players) {
		this.nome = nome;
		this.players = players;
	}
	
	public String getNome () {
		return nome;
	}
	
	public List<Player> getPlayers () {
		return players;
	}
}
