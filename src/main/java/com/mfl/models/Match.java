package com.mfl.models;

import java.util.ArrayList;

//import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "MATCH_MASTER")
@NamedNativeQueries(
{@NamedNativeQuery(name="get_all_matches", query="select match_id as id, team_one_id as teamOne, team_two_id as teamTwo from MATCH_MASTER",resultSetMapping="matchMapping"),
@NamedNativeQuery(name="get_match_byID", query="select * from MATCH_MASTER where match_id=:id",resultSetMapping="matchMapping_all_fields")})
@SqlResultSetMappings(
{@SqlResultSetMapping(name="matchMapping",
		classes= {@ConstructorResult(targetClass = Match.class,
		columns= {@ColumnResult(name="id"),@ColumnResult(name="teamOne"),@ColumnResult(name="teamTwo")})}),
@SqlResultSetMapping(name="matchMapping_all_fields",
entities= {@EntityResult(entityClass=Match.class,
fields= {@FieldResult(name="id", column="match_id"),
		@FieldResult(name="teamOne", column="team_one_id"),
		@FieldResult(name="teamTwo", column="team_two_id"),
		@FieldResult(name="version", column="version"),
		@FieldResult(name="scoreOne", column="score_one"),
		@FieldResult(name="scoreTwo", column="score_two"),
		@FieldResult(name="wicketOne", column="wicket_one"),
		@FieldResult(name="wicketTwo", column="wicket_two")})})})

public class Match extends BaseEntity {
	
	@Id @GeneratedValue
	@Column (name="MATCH_ID")
	private int id;
	@OneToOne
	@JoinColumn (name="TEAM_ONE_ID")
	private IplTeam teamOne;
	@OneToOne
	@JoinColumn (name="TEAM_TWO_ID")
	private IplTeam teamTwo;
	@Column (name="SCORE_ONE")
	private int scoreOne;
	@Column (name="SCORE_TWO")
	private int scoreTwo;
	@Column (name="WICKET_ONE")
	private int wicketOne;
	@Column (name="WICKET_TWO")
	private int wicketTwo;
	/*@OneToMany
	@JoinColumn (name="PLAYER_STATS_ID")
	private Collection<PlayerStats> playerStats;*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public IplTeam getTeamOne() {
		return teamOne;
	}
	public void setTeamOne(IplTeam teamOne) {
		this.teamOne = teamOne;
	}
	public IplTeam getTeamTwo() {
		return teamTwo;
	}
	public void setTeamTwo(IplTeam teamTwo) {
		this.teamTwo = teamTwo;
	}
	public int getScoreOne() {
		return scoreOne;
	}
	public void setScoreOne(int scoreOne) {
		this.scoreOne = scoreOne;
	}
	public int getScoreTwo() {
		return scoreTwo;
	}
	public void setScoreTwo(int scoreTwo) {
		this.scoreTwo = scoreTwo;
	}
	public int getWicketOne() {
		return wicketOne;
	}
	
	public void setWicketOne(int wicketOne) {
		this.wicketOne = wicketOne;
	}
	
	public int getWicketTwo() {
		return wicketTwo;
	}
	public void setWicketTwo(int wicketTwo) {
		this.wicketTwo = wicketTwo;
	}
	public Match() {
		
	}
	public Match(int id, IplTeam iplTeam1, IplTeam iplTeam2) {
		this.id=id;
		this.teamOne=iplTeam1;
		this.teamTwo=iplTeam2;
		
	}
	/*public Collection<PlayerStats> getPlayerStats() {
		return playerStats;
	}
	public void setPlayerStats(Collection<PlayerStats> playerStats) {
		this.playerStats = playerStats;
	}*/
	@XmlRootElement(name = "matches")
	@Component
	public static class Matches{
		
		private ArrayList<Match> matches= new ArrayList<>();

		public ArrayList<Match> getMatches() {
			return matches;
		}
		@XmlElement(name="match")
		public void setMatches(ArrayList<Match> matches) {
			this.matches = matches;
		}
		
	}
}
