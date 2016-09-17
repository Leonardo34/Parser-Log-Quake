import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Parser {
	public static void main (String[] args) throws Exception {
		List<String> linhas; // Strings contendo cada linha do arquivo de LOG
		
		//Solicita Entrada do diretorio onde se encontra o LOG
		System.out.println("Digite o diretorio do Log: ");
		
		final String diretorio = new Scanner (System.in).next();
		
		boolean arquivoExiste = Files.exists(Paths.get(diretorio));
		
		//Se o arquivo existe, então lê seu conteudo
		if (arquivoExiste) {
			try {
				linhas = Files.readAllLines(Paths.get(diretorio));
				//DEBUG
				//System.out.println(linhas.size());
				ParserGame game = new ParserGame(linhas);
				List<Game> games = game.parserGames();
				
				//DEBUG
				for (Game g: games) {
					System.out.println(g.getNome());
					List<Player> players = g.getPlayers();
					for (Player p : players) {
						System.out.println(p.getNome());
					}
				}
			}
			catch (IOException e) {
				System.out.println("Erro");
				throw new Exception (e.getMessage());
			}
		}
		else {
			//Se o arquivo não existe, informa o usuario que o diretorio esta incorreto 
			System.out.println ("Diretorio Incorreto");
		}
	}
}
