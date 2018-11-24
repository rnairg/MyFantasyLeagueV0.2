package com.mfl.modules.team;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.models.Team.Teams;
import com.mfl.dao.CommonDBServicesImp;
import com.mfl.models.Team;
import com.mfl.modules.Modules;

@Service("teamModule")
public class TeamModule implements Modules<Teams> {
	
	@Autowired
	private CommonDBServicesImp<Team> tds;
	
	@Autowired
	Teams teams;

	@Override
	public Teams read() {
		teams.setTeams(tds.dBToObject("get_all_teams"));
		return teams;
	}
	@Override
	public Teams read(int i) {
		teams.setTeams(tds.dBToObject(i,"get_team_byID"));
		return teams;		
	}
	@Override
	public ArrayList<Integer> create(Teams teams) {
		this.teams=teams;
		return tds.objectToDB(this.teams.getTeams());
	}
	@Override
	public void update(Teams teams) {
		this.teams=teams;
		tds.objectToDB(this.teams.getTeams(),"update");
	}
	@Override
	public void delete(Teams teams) {
		this.teams=teams;
		tds.objectToDB(this.teams.getTeams(),"update");
	}
}
