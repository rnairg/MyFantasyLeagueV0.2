package com.mfl.models;

import java.util.ArrayList;

import javax.persistence.Column;
//import javax.persistence.UniqueConstraint;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Entity (name="player")
@Table (name="PLAYER_MASTER")//,uniqueConstraints=@UniqueConstraint(columnNames= {"PLAYER_NAME","PLAYER_IPL_TEAM"}))
public class Player { //Model for Table PLAYER_MASTER
	
	@Id @GeneratedValue
	@Column (name = "PLAYER_ID")
	private int id;
	
	@Column (name = "PLAYER_NAME")
	private String name;
	
	@Column (name = "PLAYER_CATEGORY")
	private String category;
	
	/*@JoinColumn (name = "PLAYER_IPL_TEAM_ID")
	private IplTeam iplTeam;*/
	
	/*@ManyToMany (mappedBy ="players")
	//@ManyToOne (mappedBy ="players")
	private Collection<Team> teams = new ArrayList<Team>();
	
	@OneToOne (mappedBy ="players")
	private Collection<IplTeam> iplteams = new ArrayList<IplTeam>();
	
	public Collection<IplTeam> getIplteams() {
		return iplteams;
	}
	
	@XmlTransient
	public void setIplteams(Collection<IplTeam> iplteams) {
		this.iplteams = iplteams;
	}
	public Collection<Team> getTeams() {
		return teams;
	}
	//@XmlElement(name="team")
	@XmlTransient
	public void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}*/
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	/*public IplTeam getIplTeam() {
		return iplTeam;
	}
	public void setIplTeam(IplTeam iplTeam) {
		this.iplTeam = iplTeam;
	}*/
	
	public Player(int id, String name,String category)
	{
		setId(id);
		setName(name);
		setCategory(category);
	}
	public Player() {
		
	}
	@XmlRootElement(name="players")
	@Component
	public static class Players { //Model for Player XML
		
		private ArrayList<Player> players = new ArrayList<Player>();
		public ArrayList<Player> getPlayers() {
			return players;
		}
		
		@XmlElement(name="player")
		public void setPlayers(ArrayList<Player> players) {
			this.players = players;
		}
		
		public void createPlayerList(Player player)
		{
			this.players.add(player);
		}
		
		public void displayPlayers()
		{
			System.out.println("Players:"+getPlayers().size());
			for(int i=0;i<getPlayers().size();i++)
			{
				System.out.println("------\nID = "+getPlayers().get(i).getId()+
						"\nName = "+getPlayers().get(i).getName()+
						"\nCategory = "+getPlayers().get(i).getCategory());
			}
		}

	}
	
}
