package com.voxelboxstudios.devathlon.team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Teams {

	/** Calculate **/
	
	public static Map<Player, Team> calculate() {
		/** Teams **/
		
		Map<Player, Team> teams = new HashMap<Player, Team>();
		
		
		/** Available teams **/
		
		List<Team> available_teams = Arrays.asList(new Team[] { Team.RED, Team.BLUE, Team.GREEN, Team.YELLOW });
		
		
		/** Shuffle teams **/
		
		Collections.shuffle(available_teams);
		
		
		/** Players **/
		
		List<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
		
		
		/** Shuffle players **/
		
		Collections.shuffle(players);
		
		
		/** Sort teams out **/
		
		int i = 0;
		
		int state = 1;
		
		for(Player p : players) {
			/** Put into hashmap **/
			
			teams.put(p, available_teams.get(i));
			
			if(state % 2 == 0) {
				i++;
			}
			
			state++;
		}
		
		
		/** Return players **/
		
		return teams;
	}
	
}
