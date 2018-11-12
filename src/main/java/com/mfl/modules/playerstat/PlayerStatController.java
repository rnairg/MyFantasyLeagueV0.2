package com.mfl.modules.playerstat;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfl.models.PlayerStat.PlayerStats;
import com.mfl.modules.Modules;
@RestController
public class PlayerStatController {
	
	@Autowired
	private Modules playerStatModule;

	@RequestMapping("/playerStats")
	public PlayerStats getPlayerStats()
	{
		return (PlayerStats) playerStatModule.read();
	}
	
	@RequestMapping("/playerStats/playerStat/{id}")
	public PlayerStats getPlayerStats(@PathVariable("id") int id)
	{
		return (PlayerStats) playerStatModule.read(id);
	}
	
	@RequestMapping(value="/playerStats/add",method=RequestMethod.POST)
	public ArrayList<Integer> addPlayerStats(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		PlayerStats p = mapper.convertValue(o,PlayerStats.class);
		return playerStatModule.create(p);
	}
	
	@RequestMapping(value="/playerStats/delete",method=RequestMethod.DELETE)
	public ArrayList<Integer> deletePlayerStats(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		PlayerStats p = mapper.convertValue(o,PlayerStats.class);
		return playerStatModule.delete(p);
	}
	
	@RequestMapping(value="/playerStats/update",method=RequestMethod.PUT)
	public ArrayList<Integer> updatePlayerStats(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		PlayerStats p = mapper.convertValue(o,PlayerStats.class);
		return playerStatModule.update(p);
	}

}
