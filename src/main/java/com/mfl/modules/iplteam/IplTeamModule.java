package com.mfl.modules.iplteam;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfl.dao.CommonDBServicesImp;
import com.mfl.models.IplTeam;
import com.mfl.models.IplTeam.IplTeams;
import com.mfl.modules.Modules;
@Service
public class IplTeamModule implements Modules<IplTeams> {
	
	@Autowired
	private CommonDBServicesImp<IplTeam> itds;
	
	@Override
	public ArrayList<Integer> create(IplTeams iplTeams) {
		return itds.objectToDB(iplTeams.getIplTeams());
	}
	@Override
	public IplTeams read() {
		return new IplTeams(itds.dBToObject("get_all_iplTeams"));
	}
	@Override
	public IplTeams read(int i) {
		return new IplTeams(itds.dBToObject(i,"get_iplTeam_byID"));
	}
	@Override
	public void update(IplTeams iplTeams) {
		itds.objectToDB(iplTeams.getIplTeams(),"update");
	}
	@Override
	public void delete(IplTeams iplTeams) {
		itds.objectToDB(iplTeams.getIplTeams(),"update");
	}
}
