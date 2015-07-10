package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.voxelboxstudios.devathlon.team.TeamSelection;

public class ListenerInteract implements Listener {

	/** Interact **/
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		/** Check event **/
		
		if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) return;
		
		
		/** Check item **/
		
		if(e.getPlayer().getItemInHand() == null) return;
		if(!e.getPlayer().getItemInHand().hasItemMeta()) return;
		
		if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Team Auswahl")) {
			/** Open inventory **/
			
			e.getPlayer().openInventory(TeamSelection.getInventory());
		}
	}
	
}
