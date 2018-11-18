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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
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
@NamedNativeQueries(
{@NamedNativeQuery(name="get_all_iplTeams", query="select ipl_team_id as id, ipl_team_name as name from IPL_TEAM_MASTER",resultSetMapping="iplTeamMapping"),
@NamedNativeQuery(name="get_iplTeam_byID", query="select * from IPL_TEAM_MASTER where ipl_team_id=:id",resultSetMapping="iplTeamMapping_all_fields")})
@SqlResultSetMappings(
{@SqlResultSetMapping(name="iplTeamMapping",classes= {@ConstructorResult(targetClass = IplTeam.class,columns= {@ColumnResult(name="id"),@ColumnResult(name="name")})}),
@SqlResultSetMapping(name="iplTeamMapping_all_fields",entities= {@EntityResult(entityClass=IplTeam.class,fields= {@FieldResult(name="id", column="ipl_team_id"),@FieldResult(name="name", column="ipl_team_name"),@FieldResult(name="version", column="version")})})})

public class IplTeam extends BaseEntity {
	
	@Id @GeneratedValue
	@Column (name="IPL_TEAM_ID")
	private int id;
	@Column (name="IPL_TEAM_NAME")
	private String name;
	/*@Column (name="TEAM_OWNER")
	private String owner;*/
	@OneToMany (fetch=FetchType.LAZY)
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
		
	}

}
