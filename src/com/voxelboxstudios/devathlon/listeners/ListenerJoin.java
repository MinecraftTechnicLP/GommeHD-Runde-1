package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.items.ItemUtil;
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
		
		
		/** Join message **/
		
		e.setJoinMessage("§8» §6" + p.getName() + " §7hat das Spiel betreten §8[" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + "]");
		
		
		/** Clear inventory **/
		
		p.getInventory().clear();
		
		
		/** Potions **/
		
		for(PotionEffect pe : p.getActivePotionEffects()) {
			p.removePotionEffect(pe.getType());
		}
		
		
		/** Set health **/
		
		p.setHealth(20.0D);
		
		
		/** Set foodlevel **/
		
		p.setFoodLevel(20);
		
		
		/** Lobby **/
		
		if(Main.getState() == GameState.LOBBY) {
			/** Set exp **/
			
			p.setExp((1f / LobbyState.getMaxTime()) * LobbyState.getCurrentTime());
			
			
			/** Set level **/
			
			p.setLevel(LobbyState.getCurrentTime());
			
			
			/** Item **/
			
			p.getInventory().addItem(ItemUtil.getItemStack(Material.BOOK, "§6Team Auswahl §7<Rechts-Klick>", (short) 0));
			
			
			/** Teleport **/
			
			p.teleport(LobbyState.getLocation());
		}
	}
	
}
