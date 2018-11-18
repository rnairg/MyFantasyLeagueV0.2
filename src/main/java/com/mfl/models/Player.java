package com.mfl.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
//import javax.persistence.UniqueConstraint;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.ConstructorResult;
import javax.persistence.FieldResult;
import javax.persistence.EntityResult;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Entity (name="player")
@Table (name="PLAYER_MASTER")//,uniqueConstraints=@UniqueConstraint(columnNames= {"PLAYER_NAME","PLAYER_IPL_TEAM"}))
@NamedNativeQueries(
{@NamedNativeQuery(name="get_all_players", query="select player_id as id, player_name as name from PLAYER_MASTER",resultSetMapping="playerMapping"),
@NamedNativeQuery(name="get_player_byID", query="select * from PLAYER_MASTER where player_id=:id",resultSetMapping="playerMapping_all_fields")})
@SqlResultSetMappings(
{@SqlResultSetMapping(name="playerMapping",classes= {@ConstructorResult(targetClass = Player.class,columns= {@ColumnResult(name="id"),@ColumnResult(name="name")})}),
@SqlResultSetMapping(name="playerMapping_all_fields",entities= {@EntityResult(entityClass=Player.class,fields= {@FieldResult(name="id", column="player_id"),@FieldResult(name="name", column="player_name"),@FieldResult(name="category", column="player_category"),@FieldResult(name="nationality", column="player_nationality"),@FieldResult(name="version", column="version")})})})
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
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	/*public IplTeam getIplTeam() {
		return iplTeam;
	}
	public void setIplTeam(IplTeam iplTeam) {
		this.iplTeam = iplTeam;
	}*/
	
	public Player(int id, String name,String category, String nationality, int version)
	{
		super(version);
		setId(id);
		setName(name);
		setCategory(category);
		setNationality(nationality);
	}
	public Player(int id, String name)
	{
		setId(id);
		setName(name);
	}
	public Player() {
		super();
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
	}
	
}
