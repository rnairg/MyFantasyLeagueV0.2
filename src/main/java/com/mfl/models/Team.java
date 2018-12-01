package com.mfl.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

@Entity (name="team")
@Table (name="TEAM_MASTER")
@NamedQueries(
{@NamedQuery(name="get_team_byID", query="from team t where t.id=:id"),
@NamedQuery(name="get_all_teams", query="select new com.mfl.models.Team(t.id, t.name, t.owner) from team t")})

public class Team extends BaseEntity { //Model for Table TEAM_MASTER

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
	
	//@Formula(value = "sum(playerStat.points)")
	@Transient
	private int points;
				
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
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
	public int getVersion() {
		return super.getVersion();
	}
	public void setVersion(int version) {
		super.setVersion(version);
	}
	public Team(int id, String name, String owner) {
		this.id=id;
		this.name=name;
		this.owner=owner;
	}
	public Team(int id, String name, String owner, int points) {
		this.id=id;
		this.name=name;
		this.owner=owner;
		this.points=points;
	}
	public Team() {
		
	}
	
	@XmlRootElement(name = "teams") //Model Class for Teams XML
	@Component
	public static class Teams{
		
		@XmlElement(name = "team")
		private List<Team> teams = new ArrayList<Team>();
		
		public Teams(ArrayList<Team> teams) {
			this.teams=teams;
		}

		public List<Team> getTeams() {
			return teams;
		}
	}
}
