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
	
	@Autowired
	private Players players;

	@Override
	public Players read() {
		players.setPlayers(pds.dBToObject("get_all_players"));
		return players;
	}
	@Override
	public Players read(int i) {
		players.setPlayers(pds.dBToObject(i,"get_player_byID"));;
		return players;
	}
	@Override
	public ArrayList<Integer> create(Players players) {
		this.players=players;
		return pds.objectToDB(this.players.getPlayers());
	}
	@Override
	public void update(Players players) {
		this.players=players;
		pds.objectToDB(this.players.getPlayers(),"update");
	}
	@Override
	public void delete(Players players) {
		this.players=players;
		pds.objectToDB(this.players.getPlayers(),"delete");	
	}
}

