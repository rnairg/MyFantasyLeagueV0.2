package com.mfl.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Entity
@Table (name="PLAYER_STATS")
@NamedNativeQueries(
{@NamedNativeQuery(name="get_all_playerStats", query="select player_stat_id as id, player_id as player from PLAYER_STATS",resultSetMapping="playerStatsMapping"),
@NamedNativeQuery(name="get_playerStat_byID", query="select * from PLAYER_STATS where player_stat_id=:id",resultSetMapping="playerStatsMapping_all_fields")})
@SqlResultSetMappings(
{@SqlResultSetMapping(name="playerStatsMapping",classes= {@ConstructorResult(targetClass = PlayerStat.class,columns= {@ColumnResult(name="id"),@ColumnResult(name="player")})}),
@SqlResultSetMapping(name="playerStatsMapping_all_fields",
entities= {@EntityResult(entityClass=PlayerStat.class,fields= 
		{@FieldResult(name="id", column="player_stat_id"),
		@FieldResult(name="player", column="player_id"),
		@FieldResult(name="match", column="match_id"),
		@FieldResult(name="wickets", column="wickets"),
		@FieldResult(name="catches", column="catches"),
		@FieldResult(name="runs", column="runs"),
		@FieldResult(name="version", column="version")})})})
public class PlayerStat extends BaseEntity {
	
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
	public PlayerStat(int id, Player player) {
		
		this.id=id;
		this.player=player;
		
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	@XmlRootElement(name = "playerStats")//Model Class for PlayerStats XML
	@Component
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
