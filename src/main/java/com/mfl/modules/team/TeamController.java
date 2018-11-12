package com.mfl.modules.team;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfl.models.Team.Teams;
import com.mfl.modules.Modules;

@RestController
public class TeamController {
	
	@Autowired
	private Modules teamModule;

	@RequestMapping("/teams")
	public Teams getAllTeams()
	{
		System.out.println("In Controller");
		return (Teams) teamModule.read();
		
	}
	
	@RequestMapping("/teams/team/{id}")
	public Teams getTeam(@PathVariable("id") int id)
	{
		
		return (Teams) teamModule.read(id);
		
	}
	
	@RequestMapping(value="/teams/add",method=RequestMethod.POST)
	public ArrayList<Integer> addTeams(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		Teams t = mapper.convertValue(o,Teams.class);
		return teamModule.create(t);
	}
	
	@RequestMapping(value="/teams/delete",method=RequestMethod.DELETE)
	public ArrayList<Integer> deleteTeams(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		Teams t = mapper.convertValue(o,Teams.class);
		return teamModule.delete(t);
	}
	
	@RequestMapping(value="/teams/update",method=RequestMethod.PUT)
	public ArrayList<Integer> updateTeams(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		Teams t = mapper.convertValue(o,Teams.class);
		return teamModule.update(t);
	}

}
