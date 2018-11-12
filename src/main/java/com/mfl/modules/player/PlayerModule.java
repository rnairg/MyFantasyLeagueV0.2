package com.mfl.modules.player;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.models.Player.Players;
import com.mfl.modules.Modules;

@Service
public class PlayerModule implements Modules {
	
	@Autowired
	private PlayerDBServices pds;

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
	public ArrayList<Integer> create(Object p) {
		
		//Players players = pxs.xMLToObject(xmlResource);
		pds.setPlayers((Players)p);
		pds.setAction("create");
		return pds.objectToDB();
	}
	@Override
	public ArrayList<Integer> update(Object p) {
		//Players players = pxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		pds.setPlayers((Players)p);
		pds.setAction("update");
		return pds.objectToDB();
	}
	@Override
	public ArrayList<Integer> delete(Object p) {
		//Players players = pxs.xMLToObject(xmlResource);
		//players.displayPlayers();
		pds.setPlayers((Players)p);
		pds.setAction("delete");
		return pds.objectToDB();
	}
}
