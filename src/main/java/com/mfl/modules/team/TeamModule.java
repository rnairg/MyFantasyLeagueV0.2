package com.mfl.modules.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import com.mfl.models.Team.Teams;
import com.mfl.modules.Modules;

@Service("teamModule")
public class TeamModule implements Modules {
	
	@Autowired
	private TeamXMLServices txs;
	@Autowired
	private TeamDBServices tds;

	/*public TeamDBServices getTds() {
		return tds;
	}

	public void setTds(TeamDBServices tds) {
		this.tds = tds;
	}

	public TeamXMLServices getTxs() {
		return txs;
	}

	public void setTxs(TeamXMLServices txs) {
		this.txs = txs;
	}*/

	@Override
	public Boolean create() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean create(Document xmlResource) {
		Teams teams = txs.xMLToObject(xmlResource);
		tds.setTeams(teams);
		tds.setAction("create");
		tds.objectToDB();
		//teams.displayTeams();
		return null;
	}
	@Override
	public Boolean create(Object o) {
		tds.setTeams((Teams)o);
		tds.setAction("create");
		tds.objectToDB();
		return null;
	}

	@Override
	public Teams read() {
		System.out.println("In Teams read");
		tds.dBToObject();
		//getTds().getTeams().displayTeams();
		//getTxs().objectToXML(getTds().getTeams());
		return tds.getTeams();
	}
	@Override
	public Teams read(int i) {
		tds.dBToObject(i);
		//getTds().getTeams().displayTeams();
		//getTxs().objectToXML(getTds().getTeams());
		return tds.getTeams();		
	}

	@Override
	public Boolean delete(Document xmlResource) {
		Teams teams = txs.xMLToObject(xmlResource);
		//players.displayPlayers();
		tds.setTeams(teams);
		tds.setAction("delete");
		tds.objectToDB();
		return null;
	}
	@Override
	public Boolean delete(Object o) {
		tds.setTeams((Teams)o);
		tds.setAction("delete");
		tds.objectToDB();
		return null;
	}

	@Override
	public Boolean update(Document xmlResource) {
		Teams teams = txs.xMLToObject(xmlResource);
		//players.displayPlayers();
		tds.setTeams(teams);
		tds.setAction("update");
		tds.objectToDB();
		return null;
	}
	@Override
	public Boolean update(Object o) {
		tds.setTeams((Teams)o);
		tds.setAction("update");
		tds.objectToDB();
		return null;
	}
}
