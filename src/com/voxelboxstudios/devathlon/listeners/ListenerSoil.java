package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

public class ListenerSoil implements Listener {

	/** Soil **/
	
	@EventHandler
    public void onBlockFade(BlockFadeEvent e) {
	    e.setCancelled(true);
    }
	 
}
