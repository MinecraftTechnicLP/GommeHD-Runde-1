package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ListenerEntityInteract implements Listener {
	
	/** Entity interact entity **/
	
	@EventHandler
	public void onEntityInteractEntityEvent(PlayerInteractAtEntityEvent e) {
		/** Check right clicked **/
		
		if(e.getRightClicked() != null) {
			/** Right clicked **/
			
			Entity en = e.getRightClicked();
			
			
			/** Check if armor stand **/
			
			if(en instanceof ArmorStand) {
				/** Cancel event **/
				
				e.setCancelled(true);
			}
		}
	}

}
