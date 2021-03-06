package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.IngameState;
import com.voxelboxstudios.devathlon.team.Team;

public class ListenerRespawn implements Listener {

	/** Respawn **/
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		/** Team **/
		
		Team t = IngameState.team.get(e.getPlayer().getName());
		
		
		/** Check builders size **/
		
		if(!IngameState.arenas.contains(e.getPlayer())) {
			/** Set respawn location **/
			
			e.setRespawnLocation(Main.getMap().getOutstandingPositions().get(t));
		} else {
			/** Set respawn location **/
			
			e.setRespawnLocation(Main.getMap().getPositions().get(t));
		}
		
		
		/** Respawn location **/
		
		if(Game.respawn.containsKey(e.getPlayer().getName())) {
			/** Teleport **/
			
			e.setRespawnLocation(Game.respawn.get(e.getPlayer().getName()));
			
			
			/** Remove **/
			
			Game.respawn.remove(e.getPlayer().getName());
		}
	}
	
}
