package com.voxelboxstudios.devathlon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.voxelboxstudios.devathlon.items.ItemUtil;
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
			/** Team **/
			
			Team t = IngameState.team.get(p.getName());
			
			
			/** Check builders size **/
			
			if(t.getBuilders().size() > 0) {
				/** Remove player **/
				
				IngameState.arenas.remove(p);
				
				
				/** Set respawn location **/
				
				p.teleport(Main.getMap().getOutstandingPositions().get(t));
				
				
				/** Builder inventory **/
				
				Game.builderInventory(p);
			} else {
				/** Set respawn location **/
				
				p.teleport(Main.getMap().getPositions().get(t));
			}
		}
	}
	
	
	/** Builder inventory **/
	
	public static void builderInventory(Player p) {
		/** Items **/
		
		p.getInventory().addItem(ItemUtil.getItemStack(Material.WOOD_PICKAXE, "§eSpitzhacke", (short) 0, null));
		p.getInventory().addItem(ItemUtil.getItemStack(Material.WOOD_AXE, "§eAxt", (short) 0, null));
	}
	
}
