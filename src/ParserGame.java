import java.util.ArrayList;
import java.util.List;

public class ParserGame {
	private List<String> linhas;
	
	public ParserGame (List<String> linhas) {
		this.linhas = linhas; 
	}
	
	public List<Game> parserGames() {
		List<AnalyserGame> analyserGame = parserLinesAnalyser();
		List<Game> games = new ArrayList<>();
		
		for (AnalyserGame analyser : analyserGame) {
			//games.add(parserAnalyser(analyser));
		}
		return games;
	}
	
	private List<AnalyserGame> parserLinesAnalyser() {
		List<AnalyserGame> games = new ArrayList<>();
		int TotaldePartidas = 0;
		String UltimaLinha = linhas.get(linhas.size() - 1);
		AnalyserGame game = new AnalyserGame();
		
		for (String linha : linhas) {
			boolean InitGame = Regex.EstaPresente(linha.trim(), "InitGame");
			if (InitGame) {
				if (TotaldePartidas > 0) {
					games.add(game);
					game = new AnalyserGame ();
				}
				System.out.println(linha);//Debug 
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
	
}
