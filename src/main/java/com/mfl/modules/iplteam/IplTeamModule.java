package com.mfl.modules.iplteam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.mfl.models.IplTeam.IplTeams;
import com.mfl.modules.Modules;
@Service
public class IplTeamModule implements Modules {
	
	@Autowired
	private IplTeamXMLServices itxs;
	@Autowired
	private IplTeamDBServices itds;


	@Override
	public Boolean create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean create(Document xmlResource) {
		IplTeams iplteams = itxs.xMLToObject(xmlResource);
		itds.setIplTeams(iplteams);
		itds.setAction("create");
		itds.objectToDB();
		//teams.displayTeams();
		return null;
	}
	@Override
	public Boolean create(Object o) {
		itds.setIplTeams((IplTeams)o);
		itds.setAction("create");
		itds.objectToDB();
		return null;
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
	public Boolean delete(Document xmlResource) {
		IplTeams iplteams = itxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		itds.setIplTeams(iplteams);
		itds.setAction("delete");
		itds.objectToDB();
		return null;
	}
	@Override
	public Boolean delete(Object o) {
		itds.setIplTeams((IplTeams)o);
		itds.setAction("delete");
		itds.objectToDB();
		return null;
	}
	
	@Override
	public Boolean update(Document xmlResource) {
		IplTeams iplteams = itxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		itds.setIplTeams(iplteams);
		itds.setAction("update");
		itds.objectToDB();
		return null;
	}
	@Override
	public Boolean update(Object o) {
		itds.setIplTeams((IplTeams)o);
		itds.setAction("update");
		itds.objectToDB();
		return null;
	}


}
