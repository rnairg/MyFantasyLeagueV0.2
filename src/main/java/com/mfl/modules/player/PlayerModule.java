package com.mfl.modules.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.mfl.models.Player.Players;
import com.mfl.modules.Modules;

@Service
public class PlayerModule implements Modules {
	
	@Autowired
	private PlayerXMLServices pxs;
	@Autowired
	private PlayerDBServices pds;

	@Override
	public Boolean create() {
		
		return null;
	}

	@Override
	public Boolean delete(Document xmlResource) {
		Players players = pxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		pds.setPlayers(players);
		pds.setAction("delete");
		pds.objectToDB();
		return null;
	}
	@Override
	public Boolean delete(Object p) {
		//Players players = pxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		pds.setPlayers((Players)p);
		pds.setAction("delete");
		pds.objectToDB();
		return null;
	}

	@Override
	public Players read() {
		pds.dBToObject();
		//getPds().getPlayers().displayPlayers();
		//getPxs().objectToXML(getPds().getPlayers());
		
		System.out.println("In read");
		return pds.getPlayers();

	}
	@Override
	public Players read(int i) {
		pds.dBToObject(i);
		//getPds().getPlayers().displayPlayers();
		//pxs.objectToXML(pds.getPlayers());
		return pds.getPlayers();

	}
	
	@Override
	public Boolean create(Document xmlResource) {
		
		Players players = pxs.xMLToObject(xmlResource);
		pds.setPlayers(players);
		pds.setAction("create");
		pds.objectToDB();
		
		return null;
	}
	@Override
	public Boolean create(Object p) {
		
		//Players players = pxs.xMLToObject(xmlResource);
		pds.setPlayers((Players)p);
		pds.setAction("create");
		pds.objectToDB();
		
		return null;
	}

	@Override
	public Boolean update(Document xmlResource) {
		Players players = pxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		pds.setPlayers(players);
		pds.setAction("update");
		pds.objectToDB();
		return null;
	}
	
	@Override
	public Boolean update(Object p) {
		//Players players = pxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		pds.setPlayers((Players)p);
		pds.setAction("update");
		pds.objectToDB();
		return null;
	}

}
