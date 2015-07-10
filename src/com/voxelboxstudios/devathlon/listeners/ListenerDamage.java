package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerDamage implements Listener {

	/** Damage **/
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		/** Lobby **/
		
		if(Main.state == GameState.LOBBY) {
			e.setCancelled(true);
		} else {
			/** Player **/
			
			if(e.getEntity() instanceof Player) {
				/** Player **/
				
				Player p = (Player) e.getEntity();
				
				
				/** Spectator **/
				
				if(Game.spectators.contains(p.getName())) {
					e.setCancelled(true);
				}
				
				
				/** Ingame **/
				
				if(!IngameState.arenas.contains(p)) {
					e.setCancelled(true);
				}
			}
		}
	}
	
}
