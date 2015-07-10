package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class ListenerItemMove implements Listener {
	
	/** Item move **/
	
	@EventHandler
	public void onItemMove(InventoryMoveItemEvent e) {
		System.out.println(e.getItem().toString());
	}

}
