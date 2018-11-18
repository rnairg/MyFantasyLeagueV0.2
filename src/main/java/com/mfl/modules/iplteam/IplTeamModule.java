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
	
	@Autowired
	private IplTeams iplTeams;

	@Override
	public ArrayList<Integer> create(IplTeams iplTeams) {
		this.iplTeams=iplTeams;
		return itds.objectToDB(this.iplTeams.getIplTeams());
	}
	@Override
	public IplTeams read() {
		iplTeams.setIplTeams(itds.dBToObject("get_all_iplTeams"));
		return iplTeams;
	}
	@Override
	public IplTeams read(int i) {
		iplTeams.setIplTeams(itds.dBToObject(i,"get_iplTeam_byID"));;
		return iplTeams;
	}
	@Override
	public void update(IplTeams iplTeams) {
		this.iplTeams=iplTeams;
		itds.objectToDB(this.iplTeams.getIplTeams(),"update");
	}
	@Override
	public void delete(IplTeams iplTeams) {
		this.iplTeams=iplTeams;
		itds.objectToDB(this.iplTeams.getIplTeams(),"update");
	}
}
