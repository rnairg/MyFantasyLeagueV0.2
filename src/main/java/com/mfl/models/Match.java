package com.mfl.models;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.stereotype.Component;

@Entity (name="match")
@Table(name = "MATCH_MASTER")
@NamedQueries(
{@NamedQuery(name="get_match_byID", query="from match where id=:id"),
@NamedQuery(name="get_all_matches", query="select new com.mfl.models.Match(m.id) from match m")})

public class Match extends BaseEntity {
	
	@Id @GeneratedValue
	@Column (name="MATCH_ID")
	private int id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn (name="TEAM_ONE_ID")
	private IplTeam teamOne;
	@OneToOne(fetch=FetchType.EAGER)
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
	public int getVersion() {
		return super.getVersion();
	}
	public void setVersion(int version) {
		super.setVersion(version);
	}
	public Match() {
		
	}
	public Match(int id/*, IplTeam iplTeam1, IplTeam iplTeam2*/) {
		this.id=id;
		//this.teamOne=iplTeam1;
		//this.teamTwo=iplTeam2;
		
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
		
		@XmlElement(name="match")
		private List<Match> matches= new ArrayList<>();
		
		public Matches() {
			
		}

		public Matches(ArrayList<Match> matches) {
			this.matches=matches;
		}
		public List<Match> getMatches() {
			return matches;
		}
	}
}
