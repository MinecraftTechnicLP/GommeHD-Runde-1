package com.voxelboxstudios.devathlon.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.items.ItemUtil;
import com.voxelboxstudios.devathlon.scoreboard.Scoreboards;
import com.voxelboxstudios.devathlon.team.Team;
import com.voxelboxstudios.devathlon.team.Teams;

public class IngameState {
	
	/** Map **/
	
	public static Map<String, Team> team = new HashMap<String, Team>();
	
	/** Potions Countdown **/
	
	public static Map<Team, Integer> potions_cooldown = new HashMap<Team, Integer>();
	
	/** Arenas **/
	
	public static List<Player> arenas;
	

	/** Constructor **/
	
	public IngameState() {
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
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			/** Set level **/
			
			p.setLevel(0);
			
			
			/** Set exp **/
			
			p.setExp(0f);
			
			
			/** Teleport **/
			
			if(!arenas.contains(p)) p.teleport(Main.getMap().getOutstandingPositions().get(teams.get(p))); else p.teleport(Main.getMap().getPositions().get(teams.get(p)));
			
			
			/** Message **/
			
			if(arenas.contains(p)) {
				p.sendMessage("§6§m---------------------------------");
				p.sendMessage("§8» §6Du bist der Kämpfer!");
				p.sendMessage("§8» §7Du musst nun 30 Sekunden warten, bis das Spiel beginnt,");
				p.sendMessage("§8» §7während deine Kameraden Materialien für dich sammeln.");
				p.sendMessage("§6§m---------------------------------");
			} else {
				p.sendMessage("§6§m---------------------------------");
				p.sendMessage("§8» §7Du bist Sammler.");
				p.sendMessage("§8» §7Du musst nun so lange Materialien für ");
				p.sendMessage("§8» §e" + arena.get(teams.get(p)) + " §7sammeln, bis du Kämpfer bist.");
				p.sendMessage("§6§m---------------------------------");
			}
			
			
			/** Items **/
			
			if(!arenas.contains(p)) {
				p.getInventory().addItem(ItemUtil.getItemStack(Material.WOOD_PICKAXE, "§eSpitzhacke", (short) 0, null));
				p.getInventory().addItem(ItemUtil.getItemStack(Material.WOOD_AXE, "§eAxt", (short) 0, null));
			}
			
			
			/** Clear inventory **/
			
			p.getInventory().clear();
			
			
			/** Put into map **/
			
			team.put(p.getName(), teams.get(p));
		}
		
		
		/** Scoreboards **/
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			Scoreboards.update(p);
		}

		/** Game Scheduler **/

		new BukkitRunnable() {
			public void run() {

			}
		}.runTaskTimer(Main.getPlugin(), 0L, 20L);
	}
	
}
