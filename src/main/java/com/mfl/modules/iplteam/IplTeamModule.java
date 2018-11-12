package com.mfl.modules.iplteam;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.models.IplTeam.IplTeams;
import com.mfl.modules.Modules;
@Service
public class IplTeamModule implements Modules {
	
	@Autowired
	private IplTeamDBServices itds;

	@Override
	public ArrayList<Integer> create(Object o) {
		itds.setIplTeams((IplTeams)o);
		itds.setAction("create");
		return itds.objectToDB();
	}
	@Override
	public IplTeams read() {
		itds.dBToObject();
		//itxs.objectToXML(itds.getIplTeams());
		return itds.getIplTeams();
	}
	@Override
	public IplTeams read(int i) {
		itds.dBToObject(i);
		//getTds().getTeams().displayTeams();
		//itxs.objectToXML(itds.getIplTeams());
		return itds.getIplTeams();
	}
	@Override
	public ArrayList<Integer> update(Object o) {
		itds.setIplTeams((IplTeams)o);
		itds.setAction("update");
		return itds.objectToDB();
	}
	@Override
	public ArrayList<Integer> delete(Object o) {
		itds.setIplTeams((IplTeams)o);
		itds.setAction("delete");
		return itds.objectToDB();
	}
}
