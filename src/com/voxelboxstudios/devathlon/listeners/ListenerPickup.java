package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.voxelboxstudios.devathlon.Game;

public class ListenerPickup implements Listener {

	/** Pickup **/
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		if(Game.spectators.contains(e.getPlayer().getName())) e.setCancelled(true);
	}
	
	@EventHandler
	public void onInventoryPickup(InventoryPickupItemEvent e) {
		
		Bukkit.broadcastMessage(e.getEventName());
		
		e.setCancelled(true);
	}
	
}
