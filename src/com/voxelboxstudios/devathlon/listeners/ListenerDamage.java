package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;

public class ListenerDamage implements Listener {

	/** Damage **/
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(Main.state == GameState.LOBBY) e.setCancelled(true);
	}
	
}
