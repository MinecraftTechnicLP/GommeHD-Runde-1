package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.GameMode;
import org.bukkit.Sound;
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
		/** Cancel **/
		
		if(Main.getState() == GameState.LOBBY && e.getWhoClicked().getGameMode() != GameMode.CREATIVE) e.setCancelled(true);
		
		
		/** Team selection **/
		
		if(e.getInventory().getName().contains("Team Auswahl")) {
			/** Join Team **/
			
			Player p = (Player) e.getWhoClicked();
			
			
			/** Item **/
			
			ItemStack item = e.getCurrentItem();
			
			
			/** Inventory **/
			
			Inventory inv = e.getClickedInventory();
			
			
			/** Close inventory **/
			
			p.closeInventory();
			
			
			/** Remove **/
			
			for(ItemStack other : inv.getContents()) {
				if(other.getItemMeta().getLore().contains("§7" + p.getName())) {
					other.getItemMeta().getLore().remove("§7" + p.getName());	
				}
			}
			
			
			/** Lore **/
			
			item.getItemMeta().getLore().add("§7" + p.getName());
			
			
			/** Send message **/
			
			p.sendMessage(Main.prefix + "Ausgewähltes Team: " + item.getItemMeta().getDisplayName());
			
			
			/** Play sound **/
			
			p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 3);
		}
	}
	
}
