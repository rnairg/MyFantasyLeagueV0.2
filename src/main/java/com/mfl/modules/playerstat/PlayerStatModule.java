package com.mfl.modules.playerstat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.mfl.models.PlayerStat.PlayerStats;
import com.mfl.modules.Modules;
@Service
public class PlayerStatModule implements Modules {
	@Autowired
	private PlayerStatXMLServices psxs;
	@Autowired
	private PlayerStatDBServices psds;

	@Override
	public Boolean create() {
		
		return null;
	}

	@Override
	public Boolean create(Document xmlResource) {
		PlayerStats playerStat = psxs.xMLToObject(xmlResource);
		psds.setPlayerStats(playerStat);
		psds.setAction("create");
		psds.objectToDB();
		//teams.displayTeams();
		return null;
	}

	@Override
	public Boolean update(Document xmlResource) {
		PlayerStats playerStat = psxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		psds.setPlayerStats(playerStat);
		psds.setAction("update");
		psds.objectToDB();
		return null;
	}

	@Override
	public PlayerStats read() {
		psds.dBToObject();
		//getTds().getTeams().displayTeams();
		psxs.objectToXML(psds.getPlayerStats());
		return null;

	}

	@Override
	public Boolean delete(Document xmlResource) {
		PlayerStats playerStat = psxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		psds.setPlayerStats(playerStat);
		psds.setAction("delete");
		psds.objectToDB();

		return null;
	}

	@Override
	public PlayerStats read(int i) {
		psds.dBToObject(i);
		//getTds().getTeams().displayTeams();
		psxs.objectToXML(psds.getPlayerStats());
		return null;

	}

	@Override
	public Boolean create(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
