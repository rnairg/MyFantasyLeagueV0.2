package com.mfl.models;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
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
@NamedNativeQueries(
{@NamedNativeQuery(name="get_all_teams", query="select team_id as id, team_name as name from TEAM_MASTER",resultSetMapping="teamMapping")})
@NamedQueries(
{@NamedQuery(name="get_team_byID", query="from team where team_id=:id")})
@SqlResultSetMappings(
{@SqlResultSetMapping(name="teamMapping",classes= {@ConstructorResult(targetClass = Team.class,columns= {@ColumnResult(name="id"),@ColumnResult(name="name")})}),
@SqlResultSetMapping(name="teamMapping_all_fields",
entities= {
		@EntityResult(
				entityClass=Team.class,
				fields= {
							@FieldResult(name="id", column="team_id"),
							@FieldResult(name="name", column="team_name"),
							@FieldResult(name="owner", column="team_owner"),
							@FieldResult(name="version", column="version")
						})
		})
})

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
	public Team(int id, String name) {
		this.id=id;
		this.name=name;
	}
	public Team() {
		
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
	}
}
