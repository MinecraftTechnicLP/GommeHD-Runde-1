package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerPlace implements Listener {
	
	/** Place **/
	
	@EventHandler
	public void onBreak(BlockPlaceEvent e) {
		if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			/** Cancel event **/
			
			e.setCancelled(true);
			
			
			/** Flint and steel **/
			
			if(e.getPlayer().getItemInHand() != null) {
				if(e.getPlayer().getItemInHand().getType() == Material.FLINT_AND_STEEL) {
					if(IngameState.arenas.contains(e.getPlayer())) {
						e.setCancelled(false);
					}
				}
			}
		}
	}

}
