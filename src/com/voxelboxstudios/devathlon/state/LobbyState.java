package com.voxelboxstudios.devathlon.state;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.config.GameConfiguration;
import com.voxelboxstudios.devathlon.team.TeamSelection;

public class LobbyState {
	
	/** Current time **/
	
	private static int currenttime;
	
	
	/** Minimal players **/
	
	private static int minimalplayers;
	
	
	/** Max time **/
	
	private static int time;
	
	
	/** Location **/
	
	private static Location location;
	
	
	/** Constructor **/
	
	public LobbyState() {
		/** Set state **/
		
		Main.setState(GameState.LOBBY);
		
		
		/** Team selection **/
		
		TeamSelection.setup();
		
		
		/** Location **/
		
		location = GameConfiguration.getLocation(Main.getPlugin().getConfig(), "lobby.position");
		
		
		/** Time **/
		
		time = Main.getPlugin().getConfig().getInt("lobby.time");
		
		
		/** Minimal players **/
		
		minimalplayers = Main.getPlugin().getConfig().getInt("lobby.minimal-players");
		
		
		/** Current time **/
		
		currenttime = time;
		
		
		/** Scheduler **/
		
		new BukkitRunnable() {
			public void run() {
				/** Set level and EXP **/
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					/** Set exp **/
					
					p.setExp((1f / time) * currenttime);
					
					
					/** Set level **/
					
					p.setLevel(currenttime);
				}
				
				
				/** Sound **/
				
				if(currenttime % 15 == 0 || currenttime <= 5) {
					/** Broadcast **/
					
					Bukkit.broadcastMessage(Main.prefix + "Das Spiel beginnt in §e" + currenttime + " Sekunden§7.");
					
					
					for(Player p : Bukkit.getOnlinePlayers()) {
						/** Play sound **/
						
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 3);
					}
				}
				
				
				/** Subtract time **/
				
				currenttime--;
				
				
				/** Check time **/
				
				if(currenttime == 0) {
					if(Bukkit.getOnlinePlayers().size() >= minimalplayers) {
						/** Ingame state **/
						
						new IngameState();
						
						
						/** Cancel **/
						
						cancel();
					} else {
						/** Set time **/
						
						currenttime = time - 1;
						
						
						/** Broadcast **/
						
						Bukkit.broadcastMessage(Main.prefix + "Es fehlen noch §e" + (minimalplayers - Bukkit.getOnlinePlayers().size()) + " §7Spieler!");
						
						
						/** Set level and exp **/
						
						for(Player p : Bukkit.getOnlinePlayers()) {
							/** Play sound **/
							
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 0);
						}
					}
				}
			}
		}.runTaskTimer(Main.getPlugin(), 0L, 20L);
	}

	
	/** Get current time **/
	
	public static int getCurrentTime() {
		return currenttime;
	}


	/** Get max time **/
	
	public static int getMaxTime() {
		return time;
	}
	
	
	/** Get location **/
	
	public static Location getLocation() {
		return location;
	}
	
	
	/** Get minimal players **/
	
	public static int getMinimalPlayers() {
		return minimalplayers;
	}
}
