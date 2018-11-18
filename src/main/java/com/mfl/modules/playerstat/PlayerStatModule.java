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
	
	@Autowired
	private CommonDBServicesImp<PlayerStat> psds;
	
	@Autowired
	private PlayerStats playerStats;

	@Override
	public PlayerStats read() {
		playerStats.setPlayerStats(psds.dBToObject("get_all_playerStats"));
		return playerStats;
	}
	@Override
	public PlayerStats read(int i) {
		playerStats.setPlayerStats(psds.dBToObject(i,"get_playerStats_byId"));
		return playerStats;
	}
	@Override
	public ArrayList<Integer> create(PlayerStats playerStats) {
		this.playerStats=playerStats;
		return psds.objectToDB(this.playerStats.getPlayerStats());
	}
	@Override
	public void delete(PlayerStats playerStats) {
		this.playerStats=playerStats;
		psds.objectToDB(this.playerStats.getPlayerStats(),"delete");
	}
	@Override
	public void update(PlayerStats playerStats) {
		this.playerStats=playerStats;
		psds.objectToDB(this.playerStats.getPlayerStats(),"update");
	}
}
