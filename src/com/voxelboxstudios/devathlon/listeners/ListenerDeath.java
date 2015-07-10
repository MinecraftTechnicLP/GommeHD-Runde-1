package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerDeath implements Listener {

	/** Death **/
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		/** Set death message **/
		
		e.setDeathMessage(IngameState.team.get(e.getEntity().getName()).getChatColor() + e.getEntity().getName() + " §7ist gestorben!");
		
		
		/** Lightning effect **/
		
		Main.getMap().getSpectatorSpawn().getWorld().strikeLightningEffect(Main.getMap().getSpectatorSpawn());
		
		
		/** Death **/
		
		Game.death(e.getEntity());
	}
	
}
