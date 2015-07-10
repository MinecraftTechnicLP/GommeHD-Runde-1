package com.voxelboxstudios.devathlon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
				/** New builder **/
				
				List<Player> builders = new ArrayList<Player>(t.getBuilders());
				
				
				/** Shuffle builders **/
				
				Collections.shuffle(builders);
				
				
				/** New builder **/
				
				Player b = builders.get(0);
				
				
				/** Add to arena **/
				
				IngameState.arenas.add(b);
				
				
				/** Remove player **/
				
				IngameState.arenas.remove(p);
				
				
				/** Send messages **/
				
				p.sendMessage("§6§m---------------------------------");
				p.sendMessage("§8» §7Du bist nun ein Sammler.");
				p.sendMessage("§8» §7Du musst nun selbst Materialien sammeln.");
				p.sendMessage("§6§m---------------------------------");
				
				b.sendMessage("§6§m---------------------------------");
				b.sendMessage("§8» §7Du bist nun der neue §6§lKämper§r§7.");
				b.sendMessage("§8» §7Du musst nun mit den vorhandenen Materialien kämpfen!");
				b.sendMessage("§6§m---------------------------------");
				
				
				/** Teleport **/
				
				b.teleport(Main.getMap().getPositions().get(t));
				
				
				/** Change inventory **/
				
				ItemStack[] contents = p.getInventory().getContents();
				ItemStack[] armor = p.getInventory().getArmorContents();
				
				p.getInventory().setContents(b.getInventory().getContents());
				p.getInventory().setArmorContents(b.getInventory().getArmorContents());
				
				
				/** Clear inventory **/
				
				b.getInventory().setContents(contents);
				b.getInventory().setArmorContents(armor);
				
				
				/** Set gamemode **/
				
				p.setGameMode(GameMode.SURVIVAL);
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
