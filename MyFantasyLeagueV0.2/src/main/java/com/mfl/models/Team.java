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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

@Entity
@Table (name="TEAM_MASTER")
public class Team { //Model for Table TEAM_MASTER

	@Id @GeneratedValue
	@Column (name="TEAM_ID")
	private int id;
	@Column (name="TEAM_NAME")
	private String name;
	@Column (name="TEAM_OWNER")
	private String owner;
	@ManyToMany (fetch=FetchType.EAGER)
	@JoinTable(name="TEAM_COMP",
			   joinColumns=@JoinColumn(name="TEAM_ID"),
			inverseJoinColumns=@JoinColumn(name="PLAYER_ID")
	)
	@GenericGenerator(name="increment-gen",strategy="increment")
	@CollectionId(columns= {@Column(name="TEAM_COMP_ID")},generator="increment-gen",type=@Type(type="long"))
	private Collection<Player> players = new ArrayList<Player>();
				
	public Collection<Player> getPlayers() {
		return players;
	}
	@XmlElementWrapper(name = "players")//Annotation to bind Player list of Team XML
	@XmlElement(name = "player")
	public void setPlayers(Collection<Player> players) {
		this.players = players;
	}
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@XmlRootElement(name = "teams") //Model Class for Teams XML
	@Component
	public static class Teams{
		
		private ArrayList<Team> teams = new ArrayList<Team>();
		
		public ArrayList<Team> getTeams() {
			return teams;
		}

		@XmlElement(name = "team")
		public void setTeams(ArrayList<Team> teams) {
			this.teams = teams;
		}
		
		public void displayTeams()
		{
			System.out.println("Teams:"+getTeams().size());
			for(int i=0;i<getTeams().size();i++)
			{
				System.out.println("------\nID = "+getTeams().get(i).getId()+
						"\nName = "+getTeams().get(i).getName()+
						"\nOwner = "+getTeams().get(i).getOwner());
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
