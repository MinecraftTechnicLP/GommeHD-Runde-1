package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;
import com.voxelboxstudios.devathlon.team.Team;

public class ListenerPickup implements Listener {

	/** Pickup **/
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		/** Lobby **/
		
		if(Main.getState() == GameState.LOBBY) e.setCancelled(true);
		
		
		/** Spectator **/
		
		if(Game.spectators.contains(e.getPlayer().getName())) e.setCancelled(true);
		
		
		/** Remove dropped item in arrayList **/
		
		if(IngameState.dropped_items.containsValue(e.getItem())) {
			IngameState.dropped_items.remove(e.getItem());
		}
	}
	
	
	/** Dropper pickup **/
	
	@EventHandler
	public void onInventoryPickup(InventoryPickupItemEvent e) {
		/** Check title **/
		
		if(e.getInventory().getTitle().equalsIgnoreCase("container.hopper")) {		
			if(IngameState.dropped_items.containsKey(e.getItem())) {
				/** Team **/
				
				Team team = IngameState.team.get(IngameState.dropped_items.get(e.getItem()));
				
				
				/** Player **/
				
				Player p = team.getFighter();
				
				
				/** Send message **/
				
				Bukkit.getPlayer(IngameState.dropped_items.get(e.getItem())).sendMessage("§8» §7Du hast §7" + team.getChatColor() + p.getName() + " §7das Item §e" + e.getItem().getItemStack().getType().toString() + " §7gegeben!");
				
				
				/** Add item **/
				
				p.getInventory().addItem(e.getItem().getItemStack());
				
				
				/** Play sound **/
				
				p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 3);
				
				
				/** Send message **/
				
				p.sendMessage("§8» §7Du hast ein neues §6Item (" + e.getItem().getItemStack().getType().toString() + ") §7erhalten!");
				
				
				/** Remove item **/
				
				e.getItem().remove();
			}
			
			
			/** Cancel event **/
			
			e.setCancelled(true);
		}
	}
	
}
