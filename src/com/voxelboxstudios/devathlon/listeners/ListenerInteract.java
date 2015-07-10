package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.team.TeamSelection;

public class ListenerInteract implements Listener {

	/** Interact **/
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		/** Player **/
		
		Player p = e.getPlayer();
		
		/** Check event **/
		
		if(Main.getState() == GameState.LOBBY) { e.setCancelled(true); }
		
		if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) return;
		
		/** Get Random Potion **/
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.BREWING_STAND){
			
			if(p.getInventory().contains(new ItemStack(Material.DIAMOND, 1))){
				
			}else{
				
			}
			
		}
		
		/** Check item **/
		
		if(e.getPlayer().getItemInHand() == null) return;
		if(!e.getPlayer().getItemInHand().hasItemMeta()) return;
		
		if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Team Auswahl")) {
			/** Open inventory **/
			
			e.getPlayer().openInventory(TeamSelection.getInventory());
		}
	}
	
}
