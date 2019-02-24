package com.mfl.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Entity(name = "teamPlayerStat")
@Table(name = "TEAM_PLAYER_STATS")
public class TeamPlayerStat extends PlayerStat {
	
	@OneToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;
	
	@Column(name="TEAM_POINTS")
	private int teamPoints;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getTeamPoints() {
		return teamPoints;
	}

	public void setTeamPoints(int teamPoints) {
		this.teamPoints = teamPoints;
	}
	
	public TeamPlayerStat() {
	}

	public TeamPlayerStat(int id) {
		super(id);
	}

	public TeamPlayerStat(int playerStatId, Team t, int points) {
		super(playerStatId);
		this.team = t;
		this.teamPoints = points;
	}

	@XmlRootElement(name = "playerStats")//Model Class for PlayerStats XML
	@Component
	public static class TeamPlayerStats{
		
		@XmlElement(name = "playerStat")
		private List<TeamPlayerStat> teamPlayerStats = new ArrayList<TeamPlayerStat>();
		
		public TeamPlayerStats(){

		}
		
		public TeamPlayerStats(ArrayList<TeamPlayerStat> playerStats){
			this.teamPlayerStats=playerStats;
		}

		public List<TeamPlayerStat> getPlayerStats() {
			return teamPlayerStats;
		}
	}
		
}
