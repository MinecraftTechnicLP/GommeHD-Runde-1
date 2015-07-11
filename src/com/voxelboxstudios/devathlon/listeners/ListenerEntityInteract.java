package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ListenerEntityInteract implements Listener {
	
	@EventHandler
	public void onEntityInteractEntityEvent(PlayerInteractEntityEvent e) {
		
		Entity en = ((PlayerInteractEntityEvent) e).getRightClicked();
		
		if(en instanceof ArmorStand) {
			e.setCancelled(true);
		}
		
	}

}
