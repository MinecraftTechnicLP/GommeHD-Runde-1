package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerIgnite implements Listener {

	/** Ignite **/
	
	@EventHandler
	public void onIgnite(BlockIgniteEvent e) {
		/** Check if player is builder **/
		
		if(!IngameState.arenas.contains(e.getPlayer())) {
			e.setCancelled(true);
		} else {
			e.setCancelled(false);
		}
	}
	
}
