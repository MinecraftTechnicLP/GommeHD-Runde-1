package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;

public class ListenerClick implements Listener {

	/** Click **/
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(Main.getState() == GameState.LOBBY && e.getWhoClicked().getGameMode() != GameMode.CREATIVE) e.setCancelled(true);
		
		if(e.getInventory().getName().contains("Team Auswahl")) {
			
			/** Join Team **/
			
			Player p = (Player) e.getWhoClicked();
			ItemStack item = e.getCurrentItem();
			Inventory inv = e.getClickedInventory();
			p.closeInventory();
			item.getItemMeta().getLore().add(p.getName());
			
			for(ItemStack other : inv.getContents()) {
				
				other.getItemMeta().getLore().remove(p.getName());
				
			}
		}
	}
	
}
