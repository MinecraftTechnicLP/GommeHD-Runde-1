package com.voxelboxstudios.devathlon.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

public class GameConfiguration extends FileConfiguration {

	/** Build header **/
	
	@Override
	protected String buildHeader() {
		return null;
	}

	
	/** Load from string **/
	
	@Override
	public void loadFromString(String arg0) throws InvalidConfigurationException {}

	
	/** Save to string **/
	
	@Override
	public String saveToString() {
		return null;
	}

	
	/** Get location **/
	
	public Location getLocation(String path) {
		/** World **/
		
		World world = Bukkit.getWorld(getString(path + ".world"));
		
		
		/** Position **/
		
		double x = getDouble(path + ".x");
		double y = getDouble(path + ".y");
		double z = getDouble(path + ".z");
		
		float yaw = getInt(path + ".yaw");
		float pitch = getInt(path + ".pitch");
		
		
		/** Location **/
		
		Location loc = new Location(world, x, y, z, yaw, pitch);
		
		
		/** Return location **/
		
		return loc;
	}
}
