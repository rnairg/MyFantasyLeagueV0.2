package com.mfl.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

@Entity
@Table (name="IPL_TEAM_MASTER")
public class IplTeam {
	
	@Id @GeneratedValue
	@Column (name="IPL_TEAM_ID")
	private int id;
	@Column (name="TEAM_NAME")
	private String name;
	/*@Column (name="TEAM_OWNER")
	private String owner;*/
	@OneToMany (fetch=FetchType.EAGER)
	@JoinTable(name="IPL_TEAM_COMP",
			   joinColumns=@JoinColumn(name="IPL_TEAM_ID"),
			inverseJoinColumns=@JoinColumn(name="PLAYER_ID")
	)
	@GenericGenerator(name="increment-gen",strategy="increment")
	@CollectionId(columns= {@Column(name="IPL_TEAM_COMP_ID")},generator="increment-gen",type=@Type(type="long"))
	private Collection<Player> players = new ArrayList<Player>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Player> getPlayers() {
		return players;
	}
	@XmlElementWrapper(name = "players")//Annotation to bind Player list of Team XML
	@XmlElement(name = "player")
	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}
	
	@XmlRootElement(name = "iplTeams") //Model Class for Teams XML
	@Component
	public static class IplTeams{
		
		private ArrayList<IplTeam> teams = new ArrayList<IplTeam>();
		
		public ArrayList<IplTeam> getIplTeams() {
			return teams;
		}

		@XmlElement(name = "iplTeam")
		public void setIplTeams(ArrayList<IplTeam> teams) {
			this.teams = teams;
		}
		
		public void displayIplTeams()
		{
			System.out.println("Ipl Teams:"+getIplTeams().size());
			for(int i=0;i<getIplTeams().size();i++)
			{
				System.out.println("------\nID = "+getIplTeams().get(i).getId()+
						"\nName = "+getIplTeams().get(i).getName());
						/*"\nTeam Composition ="+getTeams().get(i).getTeamComp().size());
				for(int j=0;j<getTeams().get(i).getTeamComp().size();j++)
				{
					//System.out.println("Comp ID:"+((ArrayList<TeamComp>) getTeams().get(i).getTeamComp()).get(j).getId());
					//System.out.println("Team ID:"+((ArrayList<TeamComp>) getTeams().get(i).getTeamComp()).get(j).getTeamId());
					//System.out.println("Player ID:"+((ArrayList<TeamComp>) getTeams().get(i).getTeamComp()).get(j).getPlayerId());
					System.out.println("Data Load Complete... Display Object are WIP");
				}*/
			}
						
		}
		
		
	}

}
