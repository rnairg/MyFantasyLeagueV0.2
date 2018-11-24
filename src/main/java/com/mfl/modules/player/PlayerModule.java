package com.mfl.modules.player;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfl.models.Player.Players;
import com.mfl.dao.CommonDBServicesImp;
import com.mfl.models.Player;
import com.mfl.modules.Modules;

@Service
public class PlayerModule implements Modules <Players> {
	
	@Autowired
	private CommonDBServicesImp<Player> pds;
	
	@Override
	public Players read() {
		return new Players(pds.dBToObject("get_all_players"));
	}
	@Override
	public Players read(int i) {
		return new Players(pds.dBToObject(i,"get_player_byID"));
	}
	@Override
	public ArrayList<Integer> create(Players players) {
		return pds.objectToDB(players.getPlayers());
	}
	@Override
	public void update(Players players) {
		pds.objectToDB(players.getPlayers(),"update");
	}
	@Override
	public void delete(Players players) {
		pds.objectToDB(players.getPlayers(),"delete");	
	}
}

