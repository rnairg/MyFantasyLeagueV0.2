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
import javax.persistence.OneToMany;
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

@Entity (name="iplTeam")
@Table (name="IPL_TEAM_MASTER")
@NamedQueries(
{@NamedQuery(name="get_iplTeam_byID", query="from iplTeam where id=:id"),
@NamedQuery(name="get_all_iplTeams", query="select new com.mfl.models.IplTeam(it.id,it.name) from iplTeam it")})

public class IplTeam extends BaseEntity {
	
	@Id @GeneratedValue
	@Column (name="IPL_TEAM_ID")
	private int id;
	@Column (name="IPL_TEAM_NAME")
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
	public int getVersion() {
		return super.getVersion();
	}
	public void setVersion(int version) {
		super.setVersion(version);
	}
	public IplTeam() {
		
	}
	public IplTeam(int id, String name) {
		this.id=id;
		this.name=name;
	}
	@XmlRootElement(name = "iplTeams") //Model Class for Teams XML
	@Component
	public static class IplTeams{
		
		@XmlElement(name = "iplTeam")
		private List<IplTeam> iplTeams = new ArrayList<IplTeam>();
		
		public IplTeams(ArrayList<IplTeam> iplTeams) {
			this.iplTeams=iplTeams;
		}

		public List<IplTeam> getIplTeams() {
			return iplTeams;
		}	
		
	}

}
