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
		System.out.println(player);
		if (player.length() > 0) {
			playerID = player.substring(0, 1);
			//Debug
			//System.out.println(playerID);
		}
		else {
			playerID = "";
		}
	}
	
	private static void analyserKill (Matcher Kill, Map<String, Player> players) {
		
	}
}
