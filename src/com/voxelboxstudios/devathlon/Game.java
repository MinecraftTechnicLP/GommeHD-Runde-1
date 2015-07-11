package com.voxelboxstudios.devathlon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.voxelboxstudios.devathlon.hologram.ArmorStandManager;
import com.voxelboxstudios.devathlon.items.ItemUtil;
import com.voxelboxstudios.devathlon.state.IngameState;
import com.voxelboxstudios.devathlon.team.Team;

public class Game {

	/** Cooldown **/
	
	public static Map<String, Long> cooldown = new HashMap<String, Long>();
	
	
	/** Points **/
	
	public static Map<Team, Integer> points = new HashMap<Team, Integer>();
	
	
	/** Spectators **/
	
	public static List<String> spectators = new ArrayList<String>();
	
	
	/** Respawn **/
	
	public static Map<String, Location> respawn = new HashMap<String, Location>();

	
	/** Death **/
	
	public static void death(Player p) {
		/** Check win **/
		
		if(checkWin()) return;
		
			
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
				b.sendMessage("§8» §7Du bist nun der neue §6§lKämpfer§r§7.");
				b.sendMessage("§8» §7Du musst nun mit den vorhandenen Materialien kämpfen!");
				b.sendMessage("§6§m---------------------------------");
				
				
				/** ArmorStand **/
				
				ArmorStandManager.TeamArmorStands.get(IngameState.team.get(p.getName())).setCustomName(IngameState.team.get(p.getName()).getChatColor() + b.getName());
				
				
				/** Level **/
				
				b.setLevel(p.getLevel());
				b.setExp(p.getExp());
				
				p.setLevel(0);
				p.setExp(0f);
				
				
				/** Teleport **/
				
				respawn.put(p.getName(), b.getLocation());
				
				
				/** Cursors **/
				
				if(b.getItemOnCursor() != null) b.getInventory().addItem(b.getItemOnCursor());
				if(p.getItemOnCursor() != null) p.getInventory().addItem(p.getItemOnCursor());
				
				b.setItemOnCursor(null);
				p.setItemOnCursor(null);
				
				
				/** Cooldown **/
				
				cooldown.put(b.getName(), System.currentTimeMillis());
				
				
				/** Potions **/
				
				for(PotionEffect pe : b.getActivePotionEffects()) {
					b.removePotionEffect(pe.getType());
				}
				
				
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
	
	
	/** Check win **/
	
	public static boolean checkWin() {
		for(Team t : Team.values()) {
			if(points.get(t) == Main.getWinningPoints()) {
				
			}
		}
		
		return false;
	}
	
	
	/** Builder inventory **/
	
	public static void builderInventory(Player p) {
		/** Items **/
		
		p.getInventory().addItem(ItemUtil.getItemStack(Material.WOOD_PICKAXE, "§eSpitzhacke", (short) 0, null));
		p.getInventory().addItem(ItemUtil.getItemStack(Material.WOOD_AXE, "§eAxt", (short) 0, null));
	}
	
}
