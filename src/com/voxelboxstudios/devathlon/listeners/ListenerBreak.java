package com.voxelboxstudios.devathlon.listeners;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.voxelboxstudios.devathlon.Game;
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
		
		Material[] list = new Material[] { Material.DIAMOND_ORE, Material.IRON_ORE, Material.COAL_ORE, Material.GOLD_ORE, Material.LOG, Material.STONE, Material.GRAVEL, Material.WEB, Material.LEAVES, Material.LAPIS_ORE };
		
		
		/** Ingame **/
		
		if(Main.getState() == GameState.INGAME) {
			if(!IngameState.arenas.contains(e.getPlayer())) {
				/** Check type **/
				
				if(Arrays.asList(list).contains(e.getBlock().getType())) {
					/** Allow **/
					
					e.setCancelled(false);
					
					
					/** Gravel **/
					
					if(e.getBlock().getType() == Material.GRAVEL) {
						/** Cancel **/
						
						e.setCancelled(true);
						
						
						/** Drop item **/
						
						e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.FLINT, 1));
					}
					
					
					/** Web **/
					
					if(e.getBlock().getType() == Material.WEB) {					
						/** Drop Item  **/
						
						if(new Random().nextBoolean()) {
							e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.FEATHER, 1));
						} else {
							e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.STRING, 1));
						}
					}
					
					
					/** Leaves **/
					
					if(e.getBlock().getType() == Material.LEAVES) {					
						/** Drop Item  **/
						
						if(new Random().nextBoolean()) {
							e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.APPLE, 1));
						}
					}
					
					
					/** Respawn block **/

					if(Blocks.ticks.containsKey(e.getBlock().getType())) respawnBlock(e.getBlock());
				}
			}
		}
	}
	
	public void respawnBlock(final Block b) {
		/** Ticks **/
		
		int ticks = Blocks.getTicks(b.getType());
		
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				/** Don't block the player **/
				
				final Material type = b.getType();
				final byte data = b.getData();
				
				boolean isBlocked = false;
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(!Game.spectators.contains(p)) {
						if(p.getLocation().getBlock().getLocation() == b.getLocation() || p.getEyeLocation().getBlock().getLocation() == b.getLocation()) {
							isBlocked = true;
							break;
						}
					}	
				}
				
				Bukkit.broadcastMessage("Test: "+isBlocked);
				
				if(!isBlocked) {
					/** Type **/
					
					b.setType(type);
					
					
					/** Data **/
					
					b.setData(data);
				} else {
					respawnBlock(b);
				}
			}
		}.runTaskLater(Main.getPlugin(), ticks);
	}
}