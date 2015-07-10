package com.voxelboxstudios.devathlon.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class GameConfiguration {

	/** Get location **/
	
	public static Location getLocation(FileConfiguration cfg, String path) {
		/** World **/
		
		World world = Bukkit.getWorld(cfg.getString(path + ".world"));
		
		
		/** Position **/
		
		double x = cfg.getDouble(path + ".x");
		double y = cfg.getDouble(path + ".y");
		double z = cfg.getDouble(path + ".z");
		
		float yaw = cfg.getInt(path + ".yaw");
		float pitch = cfg.getInt(path + ".pitch");
		
		
		/** Location **/
		
		Location loc = new Location(world, x, y, z, yaw, pitch);
		
		
		/** Return location **/
		
		return loc;
	}
}
