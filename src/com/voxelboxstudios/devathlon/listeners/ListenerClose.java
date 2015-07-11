package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class ListenerClose implements Listener {

	/** Inventory close **/
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		/** Check inventory **/
		
		if(!e.getInventory().getType().equals(InventoryType.PLAYER)) {
			e.getPlayer().getInventory().addItem(e.getInventory().getContents());
		}	
	}
	
}
