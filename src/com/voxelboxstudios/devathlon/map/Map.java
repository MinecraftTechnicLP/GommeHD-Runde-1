package com.voxelboxstudios.devathlon.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;

import com.voxelboxstudios.devathlon.locations.Locations;
import com.voxelboxstudios.devathlon.mysql.SQL;
import com.voxelboxstudios.devathlon.team.Team;

public class Map {
	
	/** Positions **/
	
	private java.util.Map<Team, Location> positions;
	private java.util.Map<Team, Location> outstandingpositions;
	

	/** Name **/
	
	private String name;
	
	
	/** Spectator **/
	
	private Location spectator;
	
	
	/** Constructor **/
	
	public Map(String name, Location spectator) {
		/** Name **/
		
		this.name = name;
		
		
		/** Spectator **/
		
		this.spectator = spectator;
		
		
		/** Positions **/
		
		positions = new HashMap<Team, Location>();
		outstandingpositions = new HashMap<Team, Location>();
	}
	
	
	/** Get name **/
	
	public String getName() {
		return name;
	}

	
	/** Get spectator **/
	
	public Location getSpectatorSpawn() {
		return spectator;
	}
	
	
	/** Get positions **/
	
	public java.util.Map<Team, Location> getPositions() {
		return positions;
	}
	
	public java.util.Map<Team, Location> getOutstandingPositions() {
		return outstandingpositions;
	}
	

	/** Random map **/
	
	public static Map random() {
		/** Result set **/
		
		ResultSet rs = null;
		
		try {
			rs = SQL.getDatabase().getQuery("SELECT * FROM " + SQL.prefix + "maps");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		/** Maps **/
		
		List<Map> maps = new ArrayList<Map>();
		
		
		/** Read **/
		
		try {
			while(rs.next()) {
				/** Name **/
				
				String name = rs.getString(1);
				
				
				/** Locations **/
				
				Location blue = Locations.parseLocation(rs.getString(2));
				Location red = Locations.parseLocation(rs.getString(3));
				Location green = Locations.parseLocation(rs.getString(4));
				Location yellow = Locations.parseLocation(rs.getString(5));
				
				Location spectator = Locations.parseLocation(rs.getString(6));
				
				Location blueoutside = Locations.parseLocation(rs.getString(7));
				Location redoutside = Locations.parseLocation(rs.getString(8));
				Location greenoutside = Locations.parseLocation(rs.getString(9));
				Location yellowoutside = Locations.parseLocation(rs.getString(10));
				
				
				/** Map **/
				
				Map map = new Map(name, spectator);
				
				
				/** Locations **/
				
				map.getPositions().put(Team.BLUE, blue);
				map.getPositions().put(Team.RED, red);
				map.getPositions().put(Team.GREEN, green);
				map.getPositions().put(Team.YELLOW, yellow);
				
				map.getOutstandingPositions().put(Team.BLUE, blueoutside);
				map.getOutstandingPositions().put(Team.RED, redoutside);
				map.getOutstandingPositions().put(Team.GREEN, greenoutside);
				map.getOutstandingPositions().put(Team.YELLOW, yellowoutside);
				
				
				/** Add to maps **/
				
				maps.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		/** Shuffle maps **/
		
		Collections.shuffle(maps);
		
		
		/** Return map **/
		
		if(maps.size() > 0) return maps.get(0);
		
		return null;
	}
	
}
