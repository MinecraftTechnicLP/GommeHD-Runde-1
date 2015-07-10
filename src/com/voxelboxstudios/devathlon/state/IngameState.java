package com.voxelboxstudios.devathlon.state;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.mysql.SQL;
import com.voxelboxstudios.devathlon.scoreboard.Scoreboards;
import com.voxelboxstudios.devathlon.stats.Stats;
import com.voxelboxstudios.devathlon.team.Team;
import com.voxelboxstudios.devathlon.team.Teams;

public class IngameState {
	
	/** Map **/
	
	public static Map<String, Team> team = new HashMap<String, Team>();
	
	
	/** Potions Countdown **/
	
	public static Map<Team, Integer> potions_cooldown = new HashMap<Team, Integer>();
	
	
	/** Arenas **/
	
	public static List<Player> arenas;
	
	/** Safe Time **/
	
	public boolean issafetime = true;
	
	/** Constructor **/
	
	public IngameState() {
		/** Barriers **/
		
		setSpawnBlocks(Material.BARRIER);
		
		
		/** Points **/
		
		Game.points.put(Team.RED, 0);
		Game.points.put(Team.GREEN, 0);
		Game.points.put(Team.BLUE, 0);
		Game.points.put(Team.YELLOW, 0);
		
		
		/** Chat **/
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			for(int i = 0; i < 100; i++) p.sendMessage(" ");
			
			p.sendMessage(Main.prefix + "Das Spiel beginnt!");
		}
		
		
		/** Set state **/
		
		Main.setState(GameState.INGAME);
		
		
		/** Teams **/
		
		Map<Player, Team> teams = Teams.calculate();
		
		
		/** Players **/
		
		List<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
		
		
		/** Arena players **/
		
		arenas = new ArrayList<Player>();
		
		
		/** Shuffle players **/
		
		Collections.shuffle(players);
		
		
		/** Arena **/
		
		Map<Team, Player> arena = new HashMap<Team, Player>();
		
		boolean red = false;
		boolean blue = false;
		boolean green = false;
		boolean yellow = false;
		
		for(Player p : players) {
			if(teams.get(p) == Team.RED && !red) {
				red = true;
				arenas.add(p);
				arena.put(Team.RED, p);
			}
			
			if(teams.get(p) == Team.BLUE && !blue) {
				blue = true;
				arenas.add(p);
				arena.put(Team.BLUE, p);
			}
			
			if(teams.get(p) == Team.GREEN && !green) {
				green = true;
				arenas.add(p);
				arena.put(Team.GREEN, p);
			}
			
			if(teams.get(p) == Team.YELLOW && !yellow) {
				yellow = true;
				arenas.add(p);
				arena.put(Team.YELLOW, p);
			}
		}
		
		
		/** Loop through players **/
		
		for(final Player p : Bukkit.getOnlinePlayers()) {
			/** Stats **/
			
			new BukkitRunnable() {
				public void run() {
					/** Update **/
					
					try {
						SQL.getDatabase().queryUpdate("UPDATE " + SQL.prefix + "stats SET gamesplayed = gamesplayed+1 WHERE uuid='" + p.getUniqueId().toString() + "'");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					
					/** Load stats **/
					
					Stats.load(p);
				}
			}.runTaskAsynchronously(Main.getPlugin());
			
			
			/** Set gamemode **/
			
			p.setGameMode(GameMode.SURVIVAL);
			
			
			/** Set level **/
			
			p.setLevel(0);
			
			
			/** Set exp **/
			
			p.setExp(0f);
			
			
			/** Teleport **/
			
			if(!arenas.contains(p)) p.teleport(Main.getMap().getOutstandingPositions().get(teams.get(p))); else p.teleport(Main.getMap().getPositions().get(teams.get(p)));
			
			
			/** Put into map **/
			
			team.put(p.getName(), teams.get(p));
			
			
			/** Message **/
			
			if(arenas.contains(p)) {
				/** Send messages **/
				
				p.sendMessage("§6§m---------------------------------");
				p.sendMessage("§8» §6Du bist der Kämpfer!");
				p.sendMessage("§8» §7Du musst nun 30 Sekunden warten, bis das Spiel beginnt,");
				p.sendMessage("§8» §7während deine Kameraden Materialien für dich sammeln.");
				p.sendMessage("§6§m---------------------------------");
			} else {
				/** Send messages **/
				
				p.sendMessage("§6§m---------------------------------");
				p.sendMessage("§8» §7Du bist Sammler.");
				p.sendMessage("§8» §7Du musst nun so lange Materialien für ");
				p.sendMessage("§8» §e" + arena.get(teams.get(p)).getName() + " §7sammeln, bis du Kämpfer bist.");
				p.sendMessage("§6§m---------------------------------");
			}
			
			
			/** Clear inventory **/
			
			p.getInventory().clear();
			
			
			/** Items **/
			
			if(!arenas.contains(p)) {
				Game.builderInventory(p);
			}
		}
		
		
		/** Scoreboards **/
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			Scoreboards.update(p);
		}

		/** Build Time **/

		new BukkitRunnable() {
			public void run() { 
				/** Barriers **/
					
				setSpawnBlocks(Material.AIR);
					
					
				/** Broadcast **/
					
				Bukkit.broadcastMessage(Main.prefix + "Die Schutzzeit ist beendet!");
				
				
				/** Play sound **/
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
				}
			}
		}.runTaskLater(Main.getPlugin(), 20L * 30);
	}
	
	
	/** Spawn blocks **/
	
	public void setSpawnBlocks(Material material) {
		/** Loop **/
		
		for(Location loc : Main.getMap().getPositions().values()) {
			/** Locations **/
			
			Location[] locs = new Location[] { loc.clone().add(1, 1, 0),
					loc.clone().add(0, 1, 1),
					loc.clone().add(-1, 1, 0),
					loc.clone().add(0, 1, -1),
					loc.clone().add(0, 2, 0)};
			
			for(Location l : locs) {
				l.getBlock().setType(material);
			}
		}
	}
}
