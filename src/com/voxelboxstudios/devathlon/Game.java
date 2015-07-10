package com.voxelboxstudios.devathlon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import com.voxelboxstudios.devathlon.state.IngameState;
import com.voxelboxstudios.devathlon.team.Team;

public class Game {

	/** Points **/
	
	public static Map<Team, Integer> points = new HashMap<Team, Integer>();
	
	
	/** Spectators **/
	
	public static List<String> spectators = new ArrayList<String>();

	
	/** Death **/
	
	public static void death(Player p) {
		/** Check if player was arena player **/
		
		if(IngameState.arenas.contains(p)) {
			/** Remove player **/
			
			IngameState.arenas.remove(p);
		}
	}
	
}
