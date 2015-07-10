package com.voxelboxstudios.devathlon.blocks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import com.voxelboxstudios.devathlon.Main;

public class Blocks {
	
	/** Ticks **/
	
	private static Map<Material, Integer> ticks = new HashMap<Material, Integer>();
	

	/** Load **/
	
	public static void load() {
		/** Config **/
		
		FileConfiguration cfg = Main.getPlugin().getConfig();
		
		
		/** Add materials **/
		
		ticks.put(Material.STONE, cfg.getInt("respawntime.STONE"));
		ticks.put(Material.LOG, cfg.getInt("respawntime.LOG"));
		ticks.put(Material.DIAMOND_ORE, cfg.getInt("respawntime.DIAMOND_ORE"));
		ticks.put(Material.COAL_ORE, cfg.getInt("respawntime.COAL_ORE"));
		ticks.put(Material.IRON_ORE, cfg.getInt("respawntime.IRON_ORE"));
		ticks.put(Material.GOLD_ORE, cfg.getInt("respawntime.GOLD_ORE"));
	}
	
	
	/** Get ticks **/
	
	public static int getTicks(Material material) {
		return ticks.get(material);
	}
	
}
