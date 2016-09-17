import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class Analyser {
	public static void analyser (List<String> linhas, Map<String, Player> players) {
		for (String linha : linhas) {
			Matcher RegistroPlayer = Regex.getMatcherForSentence(linha, "ClientUserinfoChanged");
			if (RegistroPlayer.matches()) {
				analyserRegistro(RegistroPlayer, players);
				//return;
			}
			Matcher Kill = Regex.getMatcherForSentence(linha, "Kill");
			if (Kill.matches()) {
				analyserKill(Kill, players);
				//return;
			}
		}
	}
	
	private static void analyserRegistro (Matcher RegistroPlayer, Map<String, Player> players) {
		String player = RegistroPlayer.group(3).trim();
		String playerID = null;
		String playerName = null;
		//DEBUG
		//System.out.println(player);
		if (player.length() > 0) {
			playerID = player.substring(0, 1);
			int nameStart = player.indexOf("n\\");
			int nameEnd = player.indexOf("\\t\\");
			if (nameStart == 0 || nameEnd == 0) {
				playerName = "";
			}
			else {
				playerName = player.substring(nameStart + 2, nameEnd);
				//DEBUG
				//System.out.println(playerName);
			}
		}
		else {
			playerID = "";
			playerName = "";
		}
		
		if (players.containsKey(playerID)) {
			
		}
		else {
			players.put(playerID, new Player(playerID, playerName, new PlayerKD()));
		}
	}
	
	private static void analyserKill (Matcher Kill, Map<String, Player> players) {
		
	}
}
