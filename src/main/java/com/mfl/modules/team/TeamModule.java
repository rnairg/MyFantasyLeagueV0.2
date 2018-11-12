package com.mfl.modules.team;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.models.Team.Teams;
import com.mfl.modules.Modules;

@Service("teamModule")
public class TeamModule implements Modules {
	
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
	public ArrayList<Integer> create(Object o) {
		tds.setTeams((Teams)o);
		tds.setAction("create");
		return tds.objectToDB();
	}
	@Override
	public ArrayList<Integer> update(Object o) {
		tds.setTeams((Teams)o);
		tds.setAction("update");
		return tds.objectToDB();
	}
	@Override
	public ArrayList<Integer> delete(Object o) {
		tds.setTeams((Teams)o);
		tds.setAction("delete");
		return tds.objectToDB();
	}
}
