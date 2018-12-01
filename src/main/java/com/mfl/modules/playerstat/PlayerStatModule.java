package com.mfl.modules.playerstat;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.dao.CommonDBServicesImp;
import com.mfl.models.PlayerStat;
import com.mfl.models.PlayerStat.PlayerStats;
import com.mfl.modules.Modules;
@Service
public class PlayerStatModule implements Modules<PlayerStats> {
	
	private final int RUNS_FACTOR=1;
	private final int CATCHES_FACTOR=10;
	private final int WICKETS_FACTOR=25;
	@Autowired
	private CommonDBServicesImp<PlayerStat> psds;
	
	@Override
	public PlayerStats read() {
		return new PlayerStats(psds.dBToObject("get_all_playerStats"));
	}
	@Override
	public PlayerStats read(int i) {
		return new PlayerStats(psds.dBToObject(i,"get_playerStat_byID"));
	}
	@Override
	public ArrayList<Integer> create(PlayerStats playerStats) {
		return psds.objectToDB(enrichPlayerStats(playerStats).getPlayerStats());
	}
	@Override
	public void delete(PlayerStats playerStats) {
		psds.objectToDB(playerStats.getPlayerStats(),"delete");
	}
	@Override
	public void update(PlayerStats playerStats) {
		psds.objectToDB(enrichPlayerStats(playerStats).getPlayerStats(),"update");
	}
	
	private int calculatePoints(int wickets, int catches, int runs) {
		int points = (wickets*WICKETS_FACTOR)+(catches*CATCHES_FACTOR)+(runs*RUNS_FACTOR);
		return points;
	}
	private PlayerStats enrichPlayerStats(PlayerStats playerStats) {
		for(PlayerStat ps:playerStats.getPlayerStats()) {
			ps.setPoints(calculatePoints(ps.getWickets(),ps.getCatches(),ps.getRuns()));
		}
		return playerStats;
	}
}
