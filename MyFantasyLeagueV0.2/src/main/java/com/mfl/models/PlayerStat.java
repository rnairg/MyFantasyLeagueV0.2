package com.mfl.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table (name="PLAYER_STATS")
public class PlayerStat {
	
	@Id @GeneratedValue
	@Column (name="PLAYER_STATS_ID")
	private int id;
	@OneToOne
	@JoinColumn(name="PLAYER_ID")
	private Player player;
	@ManyToOne
	@JoinColumn(name="MATCH_ID")
	private Match match;
	@Column (name="WICKTES")
	private int wickets;
	@Column (name="CATCHES")
	private int catches;
	@Column (name="RUNS")
	private int runs;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public int getWickets() {
		return wickets;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	public int getCatches() {
		return catches;
	}
	public void setCatches(int catches) {
		this.catches = catches;
	}
	public int getRuns() {
		return runs;
	}
	public void setRuns(int runs) {
		this.runs = runs;
	}
	
	@XmlRootElement(name = "playerStats") //Model Class for PlayerStats XML
	public static class PlayerStats{
		
		private ArrayList<PlayerStat> playerStats = new ArrayList<PlayerStat>();

		public ArrayList<PlayerStat> getPlayerStats() {
			return playerStats;
		}
		
		@XmlElement(name = "playerStat")
		public void setPlayerStats(ArrayList<PlayerStat> playerStats) {
			this.playerStats = playerStats;
		}
		
	}
	

}
