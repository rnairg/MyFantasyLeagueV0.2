package com.mfl.modules.playerstat;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.models.PlayerStat.PlayerStats;
import com.mfl.modules.Modules;
@Service
public class PlayerStatModule implements Modules {
	
	@Autowired
	private PlayerStatDBServices psds;

	@Override
	public PlayerStats read() {
		psds.dBToObject();
		//getTds().getTeams().displayTeams();
		//psxs.objectToXML(psds.getPlayerStats());
		return psds.getPlayerStats();
	}
	@Override
	public PlayerStats read(int i) {
		psds.dBToObject(i);
		//getTds().getTeams().displayTeams();
		//psxs.objectToXML(psds.getPlayerStats());
		return psds.getPlayerStats();
	}
	@Override
	public ArrayList<Integer> create(Object o) {
		psds.setPlayerStats((PlayerStats)o);
		psds.setAction("create");
		return psds.objectToDB();
	}
	@Override
	public ArrayList<Integer> delete(Object o) {
		psds.setPlayerStats((PlayerStats)o);
		psds.setAction("delete");
		return psds.objectToDB();
	}
	@Override
	public ArrayList<Integer> update(Object o) {
		psds.setPlayerStats((PlayerStats)o);
		psds.setAction("update");
		return psds.objectToDB();
	}
}
