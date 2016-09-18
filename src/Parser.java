import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Parser {
	public static void main (String[] args) throws Exception {
		List<String> linhas; // Strings contendo cada linha do arquivo de LOG
		int opcao;
		//Solicita Entrada do diretorio onde se encontra o LOG
		System.out.println("Digite o diretorio do Log: ");
		final String diretorio = new Scanner (System.in).next();
		
		System.out.println("Apresentar relatorio com motivos de Morte ?");
		System.out.println("1: Sim");
		System.out.println("2: Não");
		opcao = new Scanner (System.in).nextInt();
		
		boolean arquivoExiste = Files.exists(Paths.get(diretorio));
		
		//Se o arquivo existe, então lê seu conteudo
		if (arquivoExiste) {
			try {
				linhas = Files.readAllLines(Paths.get(diretorio));
				//DEBUG
				//System.out.println(linhas.size());
				ParserGame game = new ParserGame(linhas);
				List<Game> games = game.parserGames();
				printGames(games, opcao);
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
	
	public static void printGames (List <Game> games, int opcao) {
		
		//Ranking de todas partidas do servidor
		System.out.println("Ranking: {");
		Map<String, Integer> totalKillsPlayer = new HashMap<>();
		List<Player> players = new ArrayList<>();
		for (Game game : games) {
			for (Player p : game.getPlayers()) {
				players.add(p);
			}
		}
		
		for (Player p : players) {
			String nome = p.getNome();
			Integer killsGame = p.getPlayerKD().getKillsValidas();
			if (!totalKillsPlayer.containsKey(nome)) {
				totalKillsPlayer.put(nome, 0);
			}
			totalKillsPlayer.put(nome, totalKillsPlayer.get(nome) + killsGame);
		}
		
		for (Entry<String, Integer> Player : totalKillsPlayer.entrySet()) {
			System.out.println(" " + Player.getKey() + ": " + Player.getValue());
		}
		System.out.println("}");
		System.out.println("");
		
		//Mostra Informações de cada partida
		for (Game game : games) {
			System.out.println(game.getNome() + " {");
			int totalKills = 0;
			for (Player player : game.getPlayers()) {
				totalKills = totalKills + player.getPlayerKD().getTotalDeaths();
			}
			System.out.println("Total_Kills: " + totalKills);
			System.out.print("players: [");
			for (Player player : game.getPlayers()) {
				System.out.print("'" + player.getNome() + "', ");
			}
			System.out.print("]");
			System.out.println("");
			System.out.println("Kills: {");
			for (Player player : game.getPlayers()) {
				System.out.println(player.getNome() + " : " + player.getPlayerKD().getKillsValidas());
			}
			System.out.println("  }");
			if (opcao == 1) {
				
			}
			System.out.println("}");
		}
	}
}
