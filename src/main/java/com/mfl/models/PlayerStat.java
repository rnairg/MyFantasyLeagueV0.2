package com.mfl.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.stereotype.Component;

@Entity (name="playerStat")
@Table (name="PLAYER_STATS")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries(
{@NamedQuery(name="get_playerStat_byID", query="from playerStat where id=:id"),
 @NamedQuery(name="get_all_playerStats", query="select new com.mfl.models.PlayerStat(ps.id) from playerStat ps")})

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
	@Column (name="WICKETS")
	private int wickets;
	@Column (name="CATCHES")
	private int catches;
	@Column (name="RUNS")
	private int runs;
	@Column (name="POINTS")
	private int points;
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
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
	public PlayerStat(int id/*, Player player*/) {
		
		this.id=id;
		//this.player=player;
		
	}
	public int getVersion() {
		return super.getVersion();
	}
	public void setVersion(int version) {
		super.setVersion(version);
	}
	public PlayerStat() {
		
	}
	
	@XmlRootElement(name = "playerStats")//Model Class for PlayerStats XML
	@Component
	public static class PlayerStats{
		
		@XmlElement(name = "playerStat")
		private List<PlayerStat> playerStats = new ArrayList<PlayerStat>();
		
		public PlayerStats(){

		}
		
		public PlayerStats(ArrayList<PlayerStat> playerStats){
			this.playerStats=playerStats;
		}

		public List<PlayerStat> getPlayerStats() {
			return playerStats;
		}
	}
}
