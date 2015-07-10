package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;

public class ListenerDrop implements Listener {

	/** Drop **/
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if(Game.spectators.contains(e.getPlayer().getName())) e.setCancelled(true);
		
		if(Main.getState() == GameState.LOBBY && e.getPlayer().getGameMode() != GameMode.CREATIVE) e.setCancelled(true);
	}
	
}
