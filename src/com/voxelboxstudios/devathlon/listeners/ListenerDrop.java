package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerDrop implements Listener {

	/** Drop **/
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if(Game.spectators.contains(e.getPlayer().getName())) e.setCancelled(true);
		
		if(Main.getState() == GameState.LOBBY && e.getPlayer().getGameMode() != GameMode.CREATIVE) e.setCancelled(true);
		
		if(IngameState.arenas.contains(e.getPlayer())) e.setCancelled(true);
		
		if(Main.getState() == GameState.INGAME && !e.isCancelled()) {
			/** Add dropped items to array list **/
			
			IngameState.dropped_items.put(e.getItemDrop(), e.getPlayer().getName());
		}
	}
	
}
