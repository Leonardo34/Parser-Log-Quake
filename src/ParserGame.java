import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserGame {
	private List<String> linhas;
	
	public ParserGame (List<String> linhas) {
		this.linhas = linhas; 
	}
	
	public List<Game> parserGames() {
		List<LinhasGame> linhasGame = parserLines();
		List<Game> games = new ArrayList<>();
		
		for (LinhasGame linhaGame : linhasGame) {
			games.add(parserLinhasGame(linhaGame));
		}
		return games;
	}
	
	private List<LinhasGame> parserLines() {
		List<LinhasGame> games = new ArrayList<>();
		int TotaldePartidas = 0;
		String UltimaLinha = linhas.get(linhas.size() - 1);
		LinhasGame game = new LinhasGame();
		
		for (String linha : linhas) {
			boolean InitGame = Regex.EstaPresente(linha.trim(), "InitGame");
			if (InitGame) {
				if (TotaldePartidas > 0) {
					games.add(game);
					game = new LinhasGame ();
				}
				TotaldePartidas++;
				game.setNome("game: "+ TotaldePartidas);
			}
			else {
				game.adicionaLinha(linha);
			}
			
			if (linha.equals(UltimaLinha)) {
				games.add(game);
			}
		}
		return games;
	}
	
	private Game parserLinhasGame (LinhasGame game) {
		List<String> linhas = game.getLinhas();
		String nome = game.getNome();
		Map<String, Player> mapPlayers = new HashMap<>();
		Analyser.analyser(linhas, mapPlayers); 
		List<Player> players = new ArrayList<>(mapPlayers.values());
		
		return new Game(nome, players);
	}	
}
