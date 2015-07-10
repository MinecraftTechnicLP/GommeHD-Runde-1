package com.voxelboxstudios.devathlon;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.voxelboxstudios.devathlon.blocks.Blocks;
import com.voxelboxstudios.devathlon.commands.*;
import com.voxelboxstudios.devathlon.listeners.*;
import com.voxelboxstudios.devathlon.map.Map;
import com.voxelboxstudios.devathlon.mysql.SQL;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.LobbyState;

public class Main extends JavaPlugin {

	/** Plugin **/
	
	private static Main plugin;
	
	
	/** State **/
	
	public static GameState state;
	
	
	/** Map **/
	
	private static Map map;
	
	
	/** Prefix **/
	
	public static String prefix = "§8» §6Mine 'n' Fight: §7";
	
	
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
		
		
		/** Check map **/
		
		getCommand("map").setExecutor(new CommandMap());
		
		
		/** Map **/
		
		map = Map.random();
		
		
		if(map == null) {
			/** Print **/
			
			Bukkit.getLogger().info("Keine Maps vorhanden! Bitte registriere eine erste Map.");
			
			
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
		pm.registerEvents(new ListenerFood(), plugin);
		pm.registerEvents(new ListenerDamage(), plugin);
		pm.registerEvents(new ListenerBreak(), plugin);
		pm.registerEvents(new ListenerPlace(), plugin);
		pm.registerEvents(new ListenerClick(), plugin);
		pm.registerEvents(new ListenerDrop(), plugin);
		pm.registerEvents(new ListenerInteract(), plugin);
		pm.registerEvents(new ListenerChat(), plugin);
		pm.registerEvents(new ListenerRespawn(), plugin);
		pm.registerEvents(new ListenerDeath(), plugin);
		
		
		/** Commands **/
		
		getCommand("stats").setExecutor(new CommandStats());
		
		
		/** Respawn time **/
		
		Blocks.load();
		
		
		/** Lobby state **/
		
		new LobbyState();
	}
	

	/** Get plugin **/
	
	public static Main getPlugin() {
		return plugin;
	}
	
	
	/** Get state **/
	
	public static GameState getState() {
		return state;
	}


	/** Set state **/
	
	public static void setState(GameState s) {
		state = s;
	}
	
	
	/** Get map **/
	
	public static Map getMap() {
		return map;
	}
	
}
