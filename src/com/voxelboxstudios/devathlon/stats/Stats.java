package com.voxelboxstudios.devathlon.stats;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.voxelboxstudios.devathlon.mysql.SQL;

public class Stats {
	
	/** Stats **/
	
	private int kills, deaths, gamesplayed, wins;
	
	
	/** HashMap **/
	
	private static Map<String, Stats> stats = new HashMap<String, Stats>();
	
	
	/** Constructor **/
	
	public Stats(int kills, int deaths, int gamesplayed, int wins) {
		/** Stats **/
		
		this.kills = kills;
		this.deaths = deaths;
		this.gamesplayed = gamesplayed;
		this.wins = wins;
	}
	
	
	/** Load stats **/
	
	public static void load(Player p) {
		try {
			ResultSet rs = SQL.getDatabase().getQuery("SELECT * FROM " + SQL.prefix + "stats WHERE uuid='" + p.getUniqueId().toString() + "'");
			
			if(!rs.next()) {
				/** Update **/
				
				SQL.getDatabase().queryUpdate("INSERT INTO " + SQL.prefix + "stats (uuid, kills, deaths, wins, gamesplayed) VALUES ('" + p.getUniqueId().toString() + "', '0', '0', '0', '0')");
				
				
				/** Put into stats **/
				
				stats.put(p.getName(), new Stats(0, 0, 0, 0));
			} else {
				/** Stats **/
				
				int kills = rs.getInt(2);
				int deaths = rs.getInt(3);
				int wins = rs.getInt(4);
				int gamesplayed = rs.getInt(5);
				
				
				/** Put into stats **/
				
				stats.put(p.getName(), new Stats(kills, deaths, gamesplayed, wins));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/** Get stats **/
	
	public static Stats getStats(Player p) {
		return stats.get(p.getName());
	}
	
	
	/** Get kills **/
	
	public int getKills() {
		return kills;
	}
	
	
	/** Get deaths **/
	
	public int getDeaths() {
		return deaths;
	}
	
	
	/** Get games played **/
	
	public int getGamesPlayed() {
		return gamesplayed;
	}
	
	
	/** Get wins **/
	
	public int getWins() {
		return wins;
	}
	
	
	/** Get KD **/
	
	public double getKD() {
		if(deaths == 0) return kills;
		if(kills == 0) return 0;
		
		return (double) kills / (double) deaths;
	}
	
	
	/** Get win chance **/
	
	public int getWinChance() {
		if(gamesplayed == 0) return 0;
		if(gamesplayed > 0 && wins == 0) return 0;
		
		return (wins / gamesplayed) * 100;
	}

}
