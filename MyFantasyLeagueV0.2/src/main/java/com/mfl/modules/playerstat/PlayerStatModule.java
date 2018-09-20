package com.mfl.modules.playerstat;

import org.w3c.dom.Document;

import com.mfl.models.PlayerStat.PlayerStats;
import com.mfl.modules.Modules;

public class PlayerStatModule implements Modules {
	
	private PlayerStatXMLServices psxs;
	private PlayerStatDBServices psds;
	
	public PlayerStatXMLServices getPsxs() {
		return psxs;
	}

	public void setPsxs(PlayerStatXMLServices psxs) {
		this.psxs = psxs;
	}

	public PlayerStatDBServices getPsds() {
		return psds;
	}

	public void setPsds(PlayerStatDBServices psds) {
		this.psds = psds;
	}

	@Override
	public Boolean create() {
		
		return null;
	}

	@Override
	public Boolean create(Document xmlResource) {
		PlayerStats playerStat = getPsxs().xMLToObject(xmlResource);
		getPsds().setPlayerStats(playerStat);
		getPsds().setAction("create");
		getPsds().objectToDB();
		//teams.displayTeams();
		return null;
	}

	@Override
	public Boolean update(Document xmlResource) {
		PlayerStats playerStat = getPsxs().xMLToObject(xmlResource);
		//players.displayPlayers();
		getPsds().setPlayerStats(playerStat);
		getPsds().setAction("update");
		getPsds().objectToDB();
		return null;
	}

	@Override
	public PlayerStats read() {
		getPsds().dBToObject();
		//getTds().getTeams().displayTeams();
		getPsxs().objectToXML(getPsds().getPlayerStats());
		return null;

	}

	@Override
	public Boolean delete(Document xmlResource) {
		PlayerStats playerStat = getPsxs().xMLToObject(xmlResource);
		//players.displayPlayers();
		getPsds().setPlayerStats(playerStat);
		getPsds().setAction("delete");
		getPsds().objectToDB();

		return null;
	}

	@Override
	public PlayerStats read(int i) {
		getPsds().dBToObject(i);
		//getTds().getTeams().displayTeams();
		getPsxs().objectToXML(getPsds().getPlayerStats());
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
