package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.voxelboxstudios.devathlon.Game;

public class ListenerDamageByEntity implements Listener {

	/** Damage **/
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		/** Check if damager is player **/
		
		if(e.getDamager() instanceof Player) {
			/** Player **/
			
			Player p = (Player) e.getDamager();
			
			
			/** Check spectator **/
			
			if(Game.spectators.contains(p.getName())) e.setCancelled(true);
		}
	}
	
}
