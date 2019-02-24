package com.mfl.modules.playerstat;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.dao.CommonDBServicesImp;
import com.mfl.models.PlayerStat;
import com.mfl.models.Player;
import com.mfl.models.Player.Players;
import com.mfl.models.PlayerStat.PlayerStats;
import com.mfl.modules.Modules;
import com.mfl.utils.Points;
@Service
public class PlayerStatModule implements Modules<PlayerStats> {
	
	@Autowired
	private CommonDBServicesImp<PlayerStat> psds;
	/*@Autowired
	private TeamStatModule tsm;*/
	@Autowired
	private Modules<Players> playerModule;
	
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
		//return tsm.create(enrichPlayerStats(playerStats).getPlayerStats());
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
	private PlayerStats enrichPlayerStats(PlayerStats playerStats) {
		for(PlayerStat ps:playerStats.getPlayerStats()) {
			Player p = playerModule.read(ps.getPlayer().getId()).getPlayers().get(0);
			ps.setPoints(Points.calculatePoints(ps,p));
		}
		return playerStats;
	}
}
