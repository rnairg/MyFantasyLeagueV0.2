package com.mfl.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.stereotype.Component;

@Entity (name="player")
@Table (name="PLAYER_MASTER")//,uniqueConstraints=@UniqueConstraint(columnNames= {"PLAYER_NAME","PLAYER_IPL_TEAM"}))
@NamedQueries(
{@NamedQuery(name="get_player_byID", query="from player as p where p.id=:id"),
@NamedQuery(name="get_all_players", query="select new com.mfl.models.Player(p.id, p.name) from player p")})

public class Player extends BaseEntity { //Model for Table PLAYER_MASTER
	
	@Id @GeneratedValue
	@Column (name = "PLAYER_ID")
	private int id;
	
	@Column (name = "PLAYER_NAME")
	private String name;
	
	@Column (name = "PLAYER_CATEGORY")
	private String category;
	
	@Column (name = "PLAYER_NATIONALITY")
	private String nationality;
	
	/*@JoinColumn (name = "PLAYER_IPL_TEAM_ID")
	private IplTeam iplTeam;*/
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
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
	public int getVersion() {
		return super.getVersion();
	}
	public void setVersion(int version) {
		super.setVersion(version);
	}
	/*public IplTeam getIplTeam() {
		return iplTeam;
	}
	public void setIplTeam(IplTeam iplTeam) {
		this.iplTeam = iplTeam;
	}*/

	public Player(int id, String name)
	{
		this.id=id;
		this.name=name;
	}
	public Player(int id)
	{
		this.id=id;
	}
	public Player() {
		super();
	}
	@XmlRootElement(name="players")
	@Component
	public static class Players { //Model for Player XML
		
		@XmlElement(name="player")
		private List<Player> players = new ArrayList<Player>();
		
		public Players() {
		}
		
		public Players(ArrayList<Player> players) {
			this.players=players;
		}
		
		public List<Player> getPlayers() {
			return players;
		}
	}
	
}
