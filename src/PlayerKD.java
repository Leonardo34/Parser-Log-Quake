import java.util.HashMap;
import java.util.Map;

public class PlayerKD {
	private Integer KillsValidas;
	private Integer TotalKills;
	private Integer TotalDeaths;
	
	private Map<TiposDeMorte, Integer> tiposDeMorte;
	
	public PlayerKD () {
		KillsValidas = 0;
		TotalKills = 0;
		TotalDeaths = 0;
		tiposDeMorte = new HashMap<>();
	}
	
	public void setKillsValidas (Integer KillsValidas) {
		this.KillsValidas = KillsValidas;
	}
	
	public Integer getKillsValidas () {
		return KillsValidas;
	}
	
	public void setTotalKills (Integer TotalKills) {
		this.TotalKills = TotalKills;
	}
	
	public Integer getTotalKills () {
		return TotalKills;
	}
	
	public void setTotalDeaths (Integer TotalDeaths) {
		this.TotalDeaths = TotalDeaths;
	}
	
	public Integer getTotalDeaths () {
		return TotalDeaths;
	}
	
	public void KillValida () {
		KillsValidas++;
	}
	
	public void Kill () {
		TotalKills++;
	}
	
	public void death () {
		TotalDeaths++;
	}
	
	public void killWorld () {
		KillsValidas--;
	}
	
	public Map<TiposDeMorte, Integer> getTiposDeMorte () {
		return tiposDeMorte;
	}
	
	public void IncrementaTipoMorte (TiposDeMorte tipo) {
		if (!tiposDeMorte.containsKey(tipo)) {
			tiposDeMorte.put(tipo,1);
		}
		else {
			Integer x = tiposDeMorte.get(tipo);
			tiposDeMorte.put(tipo, x + 1);
		}
	}
	
	
}
