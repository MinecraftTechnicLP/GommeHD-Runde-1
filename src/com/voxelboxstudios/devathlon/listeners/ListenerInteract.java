package com.voxelboxstudios.devathlon.listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.team.TeamSelection;

public class ListenerInteract implements Listener {

	/** Interact **/
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {	
		/** Player **/
		
		Player p = e.getPlayer();
		
		
		/** Physics **/
		
		if(e.getAction() == Action.PHYSICAL) e.setCancelled(true);
		

		/** Check event **/
		
		if(Main.getState() == GameState.LOBBY) { e.setCancelled(true); }
		
		if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) return;
		
		
		/** Hopper **/
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.HOPPER) e.setCancelled(true);
		
		
		/** Get random potion **/
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.BREWING_STAND) {	
			/** Cancel event **/
			
			e.setCancelled(true);
			
			
			/** Check item **/
			
			if(p.getInventory().getItemInHand().getType() == Material.DIAMOND) {
				/** Potion types **/
				
				List<PotionType> potion_types = new ArrayList<PotionType>();
				
				potion_types.add(PotionType.FIRE_RESISTANCE);
				potion_types.add(PotionType.INSTANT_DAMAGE);
				potion_types.add(PotionType.JUMP);
				potion_types.add(PotionType.NIGHT_VISION);
				potion_types.add(PotionType.REGEN);
				potion_types.add(PotionType.SPEED);
				potion_types.add(PotionType.STRENGTH);
				potion_types.add(PotionType.WEAKNESS);
				
				
				/** Shuffle potion types **/
				
				Collections.shuffle(potion_types);

				
				/** Create potion **/
				
				Potion potion = new Potion(potion_types.get(0));
				
				
				/** Set splash **/
				
				potion.setSplash(true);
				
				
				/** Set level **/
				
				potion.setLevel(new Random().nextInt(1) + 1);
				
				
				/** New item stack **/
				
				ItemStack is = potion.toItemStack(new Random().nextInt(1)+1);
				
				
				/** Remove diamond **/
				
				if(e.getPlayer().getItemInHand().getAmount() > 1) {
					e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
					e.getPlayer().getInventory().addItem(is);
				} else {
					e.getPlayer().setItemInHand(is);
				}	 
				
				
				/** Play sound **/
				
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 1, 3);
			} else {
				p.sendMessage("§8» §7Du benötigst einen Diamanten in deiner Hand für einen Trank.");
			}
		}
		
		
		/** Check item **/
		
		if(e.getPlayer().getItemInHand() == null) return;
		if(!e.getPlayer().getItemInHand().hasItemMeta()) return;
		if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null) return;
		
		if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Team Auswahl")) {
			/** Open inventory **/
			
			e.getPlayer().openInventory(TeamSelection.getInventory());
		}
	}
	
}
