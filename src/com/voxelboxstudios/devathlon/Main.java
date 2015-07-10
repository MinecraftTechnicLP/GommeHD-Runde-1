package com.voxelboxstudios.devathlon;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.voxelboxstudios.devathlon.commands.CommandStats;
import com.voxelboxstudios.devathlon.listeners.*;

import com.voxelboxstudios.devathlon.mysql.SQL;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.LobbyState;

public class Main extends JavaPlugin {

	/** Plugin **/
	
	private static Main plugin;
	
	
	/** State **/
	
	public static GameState state;
	
	
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
		
		
		/** Commands **/
		
		getCommand("stats").setExecutor(new CommandStats());
		
		
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
	
}
