package com.voxelboxstudios.devathlon.locations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Locations {

	/** Parse location **/
	
	public static Location parseLocation(String location) {
		/** Split **/
		
		String[] split = location.split(":");
		
		
		/** World **/
		
		World world = Bukkit.getWorld(split[0]);
		
		
		/** Position **/
		
		double x = Double.parseDouble(split[1]);
		double y = Double.parseDouble(split[2]);
		double z = Double.parseDouble(split[3]);
		
		
		/** Yaw & Pitch **/
		
		float yaw = Float.parseFloat(split[4]);
		float pitch = Float.parseFloat(split[5]);
		
		
		/** Return location **/
		
		return new Location(world, x, y, z, yaw, pitch);
	}
	
}
