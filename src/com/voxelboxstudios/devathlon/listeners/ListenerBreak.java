package com.voxelboxstudios.devathlon.listeners;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.blocks.Blocks;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerBreak implements Listener {
	
	/** Break **/
	
	@EventHandler
	public void onBreak(final BlockBreakEvent e) {
		/** EXP **/
		
		e.setExpToDrop(0);
		
		
		/** Cancel event **/
		
		e.setCancelled(true);
		
		
		/** Types **/
		
		Material[] list = new Material[] { Material.DIAMOND_ORE, Material.IRON_ORE, Material.COAL_ORE, Material.GOLD_ORE, Material.LOG, Material.STONE, Material.GRAVEL, Material.WEB, Material.LEAVES };
		
		
		/** Ingame **/
		
		if(Main.getState() == GameState.INGAME) {
			if(!IngameState.arenas.contains(e.getPlayer())) {
				/** Check type **/
				
				if(Arrays.asList(list).contains(e.getBlock().getType())) {
					/** Allow **/
					
					e.setCancelled(false);
					
					
					/** Respawn block **/
					
					int ticks = Blocks.getTicks(e.getBlock().getType());
					
					final Material type = e.getBlock().getType();
					
					new BukkitRunnable() {
						public void run() {
							/** Type **/
							
							e.getBlock().setType(type);
						}
					}.runTaskLater(Main.getPlugin(), ticks);
				}
			}
		}
	}

}
