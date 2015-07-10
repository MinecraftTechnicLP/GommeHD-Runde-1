package com.voxelboxstudios.devathlon;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class Worlds {
	
	/** Prepare **/
	
	public static void prepare() {
		/** Loop through worlds **/
		
		for(World w : Bukkit.getWorlds()) {
			/** Daylight cyle **/
			
			w.setGameRuleValue("doDaylightCycle", "false");
			
			
			/** Time **/
			
			w.setTime(15000);
			
			
			/** Set weather **/
			
			w.setStorm(false);
			w.setThundering(false);
		}
	}

}
