package com.mfl.modules.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfl.models.Match.Matches;
import com.mfl.modules.Modules;
@RestController
public class MatchController {
	
	@Autowired
	private Modules matchModule;

	@RequestMapping("/matches")
	public Matches getMatches()
	{
		
		return (Matches) matchModule.read();
		
	}
	
	@RequestMapping("/matches/match/{id}")
	public Matches getMatches(@PathVariable("id") int id)
	{
		
		return (Matches) matchModule.read(id);
		
	}
	
	@RequestMapping(value="/matches/add",method=RequestMethod.POST)
	public void addMatches(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		Matches m = mapper.convertValue(o,Matches.class);
		matchModule.create(m);
		
	}
	
	@RequestMapping(value="/matches/delete",method=RequestMethod.DELETE)
	public void deleteMatches(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		Matches m = mapper.convertValue(o,Matches.class);
		matchModule.delete(m);
		
	}
	
	@RequestMapping(value="/matches/update",method=RequestMethod.PUT)
	public void updateMatches(@RequestBody Object o)
	{
		ObjectMapper mapper = new ObjectMapper();
		Matches m = mapper.convertValue(o,Matches.class);
		matchModule.update(m);
		
	}

}
