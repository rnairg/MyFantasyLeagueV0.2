package com.mfl.modules.player;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfl.models.Player.Players;
import com.mfl.modules.Modules;

@CrossOrigin
@RestController
public class PlayerController {
	
	@Autowired
	private Modules<Players> playerModule;

	@RequestMapping("/players")
	public Players getAllPlayers()
	{
		
		return (Players) playerModule.read();
		
	}
	
	@RequestMapping("/players/player/{id}")
	public Players getPlayer(@PathVariable("id") int id)
	{
		
		return (Players) playerModule.read(id);
		
	}
	
	@RequestMapping(value="/players/add",method=RequestMethod.POST)
	public ArrayList<Integer> addPlayers(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		Players p = mapper.convertValue(o,Players.class);
		return playerModule.create(p);
	}
	
	@RequestMapping(value="/players/delete",method=RequestMethod.DELETE)
	public void deletePlayers(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		Players p = mapper.convertValue(o,Players.class);
		playerModule.delete(p);
	}
	
	@RequestMapping(value="/players/update",method=RequestMethod.PUT)
	public void updatePlayers(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		Players p = mapper.convertValue(o,Players.class);
		playerModule.update(p);
	}

}
