import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class Analyser {
	public static void analyser (List<String> linhas, Map<String, Player> players) {
		for (String linha : linhas) {
			Matcher RegistroPlayer = Regex.getMatcherForSentence(linha, "ClientUserinfoChanged");
			if (RegistroPlayer.matches()) {
				analyserRegistro(RegistroPlayer, players);
			}
			Matcher Kill = Regex.getMatcherForSentence(linha, "Kill");
			if (Kill.matches()) {
				analyserKill(Kill, players);
			}
		}
	}
	
	private static void analyserRegistro (Matcher RegistroPlayer, Map<String, Player> players) {
		String player = RegistroPlayer.group(3).trim();
		String playerID = null;
		String playerName = null;
		
		if (player.length() > 0) {
			playerID = player.substring(0, 1);
			int nameStart = player.indexOf("n\\");
			int nameEnd = player.indexOf("\\t\\");
			
			if (nameStart == 0 || nameEnd == 0) {
				playerName = "";
			}
			else {
				playerName = player.substring(nameStart + 2, nameEnd);
			}
		}
		
		else {
			playerID = "";
			playerName = "";
		}
		
		if (players.containsKey(playerID)) {
			Player antigo = players.get(playerID);
			
			if (!antigo.getNome().equals(playerName)) {
				players.remove(playerID);
				antigo.setID("1000" + antigo.getID());
				players.put(antigo.getID(), antigo);
				players.put(playerID, new Player (playerID, playerName, new PlayerKD()));
			}
		}
		else {
			players.put(playerID, new Player(playerID, playerName, new PlayerKD()));
		}
	}
	
	private static void analyserKill (Matcher Kill, Map<String, Player> players) {
		String playerKD = Kill.group(3).trim();
		Matcher matcherKD = Regex.createPatternForSetence("([0-9]*)\\s([0-9]*)\\s([0-9]*)(.*)", playerKD);
		if (!matcherKD.matches()) {
			return;
		}
		String idKiller = matcherKD.group(1);
		String idKilled = matcherKD.group(2);
		String type = matcherKD.group(3);
		
		if (!idKiller.equals(idKilled)) {
			Player killer = players.get(idKiller);
			if (!(killer == null)) {
				killer.getPlayerKD().Kill();
				killer.getPlayerKD().KillValida();
			}
		}
		
		Player killed = players.get(idKilled);
		if (!(killed == null)) {
			killed.getPlayerKD().death();
			killed.getPlayerKD().IncrementaTipoMorte(TiposDeMorte.getType(Integer.valueOf(type)));
		}
		
		if (idKiller.equals("1022")) {
			Player KilledWorld =  players.get(idKilled);
			if (!(KilledWorld == null)) {
				KilledWorld.getPlayerKD().killWorld();
			}
		}
	}
	
}
