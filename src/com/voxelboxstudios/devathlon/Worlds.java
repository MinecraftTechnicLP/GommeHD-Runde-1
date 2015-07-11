package com.voxelboxstudios.devathlon;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

public class Worlds {
	
	/** Prepare **/
	
	public static void prepare() {
		/** Loop through worlds **/
		
		for(World w : Bukkit.getWorlds()) {
			/** Daylight cyle **/
			
			w.setGameRuleValue("doDaylightCycle", "false");
			
			
			/** Fire tick **/
			
			w.setGameRuleValue("fireSpread", "false");
			w.setGameRuleValue("doFireTick", "false");
			
			
			/** Time **/
			
			w.setTime(15000);
			
			
			/** Set weather **/
			
			w.setStorm(false);
			w.setThundering(false);
			
			
			/** Remove items **/
			
			for(Item i : w.getEntitiesByClass(Item.class)) {
				i.remove();
			}
			
			for(Arrow i : w.getEntitiesByClass(Arrow.class)) {
				i.remove();
			}
			
			for(Entity e : w.getEntitiesByClass(ArmorStand.class)) {
				e.remove();
			}
		}
	}

}
