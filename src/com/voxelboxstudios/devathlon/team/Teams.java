package com.voxelboxstudios.devathlon.team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Teams {

	/** Calculate **/
	
	public static Map<Player, Team> calculate() {
		/** Teams **/
		
		Map<Player, Team> teams = new HashMap<Player, Team>();
		
		
		/** Overriden teams **/
		
		Inventory inv = TeamSelection.getInventory();
		
		for(ItemStack is : inv.getContents()) {
			if(is != null) {
				if(is.hasItemMeta()) {
					if(is.getItemMeta().getLore() != null) {
						Team t = null;
						
						if(is.getItemMeta().getDisplayName().startsWith(Team.BLUE.getChatColor())) t = Team.BLUE;
						if(is.getItemMeta().getDisplayName().startsWith(Team.RED.getChatColor())) t = Team.RED;
						if(is.getItemMeta().getDisplayName().startsWith(Team.GREEN.getChatColor())) t = Team.GREEN;
						if(is.getItemMeta().getDisplayName().startsWith(Team.YELLOW.getChatColor())) t = Team.YELLOW;
						
						for(Player p : Bukkit.getOnlinePlayers()) {
							if(is.getItemMeta().getLore().contains("§7" + p.getName())) {
								teams.put(p, t);
							}
						}
					}
				}
			}
		}
		
		
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
			
			if(!teams.containsKey(p)) teams.put(p, available_teams.get(i));
			
			if(state % 2 == 0) {
				i++;
			}
			
			state++;
		}
		
		
		/** Return players **/
		
		return teams;
	}
	
}
