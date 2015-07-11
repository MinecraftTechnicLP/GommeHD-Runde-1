package com.voxelboxstudios.devathlon.listeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.LobbyState;

public class ListenerClick implements Listener {

	/** Click **/
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		/** Cancel **/
		
		if(Main.getState() == GameState.LOBBY && e.getWhoClicked().getGameMode() != GameMode.CREATIVE) e.setCancelled(true);
		
		
		/** Team selection **/
		
		if(e.getClickedInventory() != null) {
			if(e.getClickedInventory().getName() != null) {
				if(e.getClickedInventory().getName().contains("Team Auswahl")) {
					/** Join Team **/
					
					Player p = (Player) e.getWhoClicked();
					
					
					/** Item **/
					
					ItemStack item = e.getCurrentItem();
					
					if(item != null) {
						if(item.hasItemMeta()) {
							/** Inventory **/
							
							Inventory inv = e.getClickedInventory();
							
							if(item.getItemMeta().getLore() != null) {
								if(item.getItemMeta().getLore().contains("§7" + p.getName())) {
									p.sendMessage(Main.prefix + "Du bist bereits in diesem Team!");
									return;
								}
								
								int max = LobbyState.getMinimalPlayers() / 4;
								
								if(max < 1) max = 1;
								
								if(item.getItemMeta().getLore().size() == max) {
									p.sendMessage(Main.prefix + "Dieses Team ist bereits voll!");
									return;
								}
							}
							
							
							/** Close inventory **/
							
							p.closeInventory();
							
							
							/** Remove **/
							
							for(ItemStack other : inv.getContents()) {
								if(other != null) {
									if(other.hasItemMeta()) {
										if(other.getItemMeta().getLore() != null) {
											if(other != item) {
												if(other.getItemMeta().getLore().contains("§7" + p.getName())) {
													ItemMeta meta = other.getItemMeta();
													List<String> lore = meta.getLore();
													lore.remove("§7" + p.getName());
													meta.setLore(lore);
													other.setItemMeta(meta);
												}											
											}
										}
									}
								}
							}
							
							
							/** Lore **/
							
							ItemMeta meta = item.getItemMeta();
							
							if(meta.getLore() == null) {
								meta.setLore(Arrays.asList("§7" + p.getName()));
							} else {
								List<String> lore = meta.getLore();
								
								if(!lore.contains("§7" + p.getName())) {
									lore.add("§7" + p.getName());
									meta.setLore(lore);
								} else {
									/** Send message **/
									
									p.sendMessage(Main.prefix + "Du bist bereits in diesem Team.");
									
								
									/** Return **/
									
									return;							
								}
							}
							
							item.setItemMeta(meta);
							
							ItemStack helmet = item;
							helmet.getItemMeta().getLore().clear();
							
							p.getInventory().setHelmet(helmet);
							
							
							/** Send message **/
							
							p.sendMessage(Main.prefix + "Ausgewähltes Team: " + item.getItemMeta().getDisplayName());
							
							
							/** Play sound **/
							
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 3);
						}
					}
				}
			}
		}
	}
	
}
