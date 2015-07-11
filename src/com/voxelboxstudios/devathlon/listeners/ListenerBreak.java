package com.voxelboxstudios.devathlon.listeners;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
	
	@SuppressWarnings("deprecation")
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

					if(Blocks.ticks.containsKey(e.getBlock().getType())) respawnBlock(e.getBlock().getLocation(), e.getBlock().getType(), e.getBlock().getData());
				}
			}
		}
	}
	
	/** Respawn Blocks **/
	
	public void respawnBlock(final Location l, final Material m, final byte data) {
		/** Ticks **/
		
		int ticks = Blocks.getTicks(m);
		
		new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				/** Don't block the player **/
				
				
				boolean isBlocked = false;
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(!Game.spectators.contains(p)) {
						if(checkLocation(p.getLocation().getBlock().getLocation(), l) || checkLocation(p.getEyeLocation().getBlock().getLocation(), l)) {
							isBlocked = true;
							break;
						}
					}	
				}
				
				if(!isBlocked) {
					/** Type **/
					
					l.getWorld().getBlockAt(l).setType(m);
					
					
					/** Data **/
					
					l.getWorld().getBlockAt(l).setData(data);
					
					
					/** Cancel **/
					
					cancel();
				} else {
					/** Cancel **/
					
					cancel();
					
					
					/** Respawn block **/
					
					respawnBlock(l, m, data);
				}
			}
		}.runTaskLater(Main.getPlugin(), ticks);
	}
	
	/** Check Location **/
	
	public boolean checkLocation(Location l, Location l2){
		if(l.getBlockX() == l2.getBlockX()){
			if(l.getBlockY() == l2.getBlockY()){
				if(l.getBlockZ() == l2.getBlockZ()){
					return true;
				}
			}
		}
		return false;
	}
}