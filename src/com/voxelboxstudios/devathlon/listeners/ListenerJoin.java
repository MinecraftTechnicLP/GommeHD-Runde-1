package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.items.ItemUtil;
import com.voxelboxstudios.devathlon.scoreboard.Scoreboards;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.LobbyState;
import com.voxelboxstudios.devathlon.stats.Stats;

public class ListenerJoin implements Listener {

	/** Join **/
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		/** Player **/
		
		final Player p = e.getPlayer();
		
		
		/** Stats **/
		
		new BukkitRunnable() { public void run() { Stats.load(p); }}.runTaskAsynchronously(Main.getPlugin());
		
		
		/** Clear inventory **/
		
		p.getInventory().clear();
		
		
		/** Armor content **/
		
		p.getInventory().setArmorContents(null);
		
		
		/** Potions **/
		
		for(PotionEffect pe : p.getActivePotionEffects()) {
			p.removePotionEffect(pe.getType());
		}
		
		
		/** Set health **/
		
		p.setHealth(20.0D);
		
		
		/** Max health **/
		
		p.setMaxHealth(20.0D);
		
		
		/** Set foodlevel **/
		
		p.setFoodLevel(20);
		
		
		/** Lobby **/
		
		if(Main.getState() == GameState.LOBBY) {
			/** Entities **/
			
			
			
			
			/** Join message **/
			
			e.setJoinMessage("§8» §6" + p.getName() + " §7hat das Spiel betreten §8[" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + "]");
			
			
			/** Set exp **/
			
			p.setExp((1f / LobbyState.getMaxTime()) * LobbyState.getCurrentTime());
			
			
			/** Set level **/
			
			p.setLevel(LobbyState.getCurrentTime());
			
			
			/** Item **/
			
			p.getInventory().addItem(ItemUtil.getItemStack(Material.BOOK, "§6Team Auswahl §7<Rechts-Klick>", (short) 0, null));
			
			
			/** Teleport **/
			
			p.teleport(LobbyState.getLocation());
			
			
			/** Scoreboard **/
			
			Scoreboards.update(p);
		} else {
			/** Join message **/
			
			e.setJoinMessage(null);
			
			
			/** Spectator **/
			
			if(!Game.spectators.contains(p.getName())) {
				/** Add as spectator **/
				
				Game.spectators.add(p.getName());
			}
			
			
			/** Set fly **/
			
			p.setAllowFlight(true);
			
			
			/** Hide **/
			
			for(Player t : Bukkit.getOnlinePlayers()) {
				t.hidePlayer(p);
				
				if(Game.spectators.contains(t.getName())) {
					p.hidePlayer(p);
				}
			}
			
			
			/** Killed **/
			
			if(!Main.killed) {
				/** Killed **/
				
				Main.killed = true;
				
				
				/** Entity **/
				
				new BukkitRunnable() { public void run() { Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e"); } }.runTaskLater(Main.getPlugin(), 20L);
			}
			
			
			/** Scoreboard **/
			
			Scoreboards.update(p);
		}
	}
	
}
