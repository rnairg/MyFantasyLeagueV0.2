package com.mfl.modules.iplteam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfl.models.IplTeam.IplTeams;
import com.mfl.modules.Modules;

@RestController
public class IplTeamController {
	
	@Autowired
	private Modules iplTeamModule;

	@RequestMapping("/iplTeams")
	public IplTeams getIplTeams()
	{
		
		return (IplTeams) iplTeamModule.read();
		
	}
	
	@RequestMapping("/iplTeams/iplTeam/{id}")
	public IplTeams getIplTeam(@PathVariable("id") int id)
	{
		
		return (IplTeams) iplTeamModule.read(id);
		
	}
	
	@RequestMapping(value="/iplTeams/add",method=RequestMethod.POST)
	public void addIplTeams(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		IplTeams it = mapper.convertValue(o,IplTeams.class);
		iplTeamModule.create(it);
		
	}
	
	@RequestMapping(value="/iplTeams/delete",method=RequestMethod.DELETE)
	public void deleteIplTeams(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		IplTeams it = mapper.convertValue(o,IplTeams.class);
		iplTeamModule.delete(it);
		
	}
	
	@RequestMapping(value="/iplTeams/update",method=RequestMethod.PUT)
	public void updateIplTeams(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		IplTeams it = mapper.convertValue(o,IplTeams.class);
		iplTeamModule.update(it);
		
	}

}
