package com.voxelboxstudios.devathlon;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.voxelboxstudios.devathlon.listeners.*;

import com.voxelboxstudios.devathlon.mysql.SQL;

public class Main extends JavaPlugin {

	/** Plugin **/
	
	private static Main plugin;
	
	
	/** Gets called when plugin is loaded **/
	
	public void onEnable() {
		/** Plugin **/
		
		plugin = this;
		
		
		/** Setup config **/
		
		saveDefaultConfig();
		
		
		/** Connect to database **/
		
		try {
			SQL.connect();
		} catch (ClassNotFoundException | SQLException e) {
			/** Log error **/
			
			Bukkit.getLogger().severe("Konnte keine Verbindung zur MySQL-Datenbank aufbauen!");
			
			
			/** Stop server **/
			
			Bukkit.shutdown();
			
			
			/** Return **/
			
			return;
		}
		
		
		/** Prepare worlds **/
		
		Worlds.prepare();
		
		
		/** Listeners **/
		
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new ListenerWeather(), plugin);
		pm.registerEvents(new ListenerJoin(), plugin);
		pm.registerEvents(new ListenerQuit(), plugin);
		pm.registerEvents(new ListenerKick(), plugin);
	}
	

	/** Get plugin **/
	
	public static Main getPlugin() {
		return plugin;
	}
	
}
