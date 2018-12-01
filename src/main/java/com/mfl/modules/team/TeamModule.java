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
	
	@Override
	public Teams read() {
		return new Teams(tds.dBToObject("get_all_teams"));
	}
	@Override
	public Teams read(int i) {
		return new Teams(tds.dBToObject(i,"get_team_byID"));		
	}
	@Override
	public ArrayList<Integer> create(Teams teams) {
		return tds.objectToDB(teams.getTeams());
	}
	@Override
	public void update(Teams teams) {
		tds.objectToDB(teams.getTeams(),"update");
	}
	@Override
	public void delete(Teams teams) {
		tds.objectToDB(teams.getTeams(),"update");
	}
}
