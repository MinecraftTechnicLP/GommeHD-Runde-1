package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;

public class ListenerKick implements Listener {

	/** Kick **/
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		/** Leave message **/
		
		e.setLeaveMessage("§8» §6" + e.getPlayer().getName() + " §7hat das Spiel verlassen §8[" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + "]");
		
		
		/** Spectators **/
		
		if(Game.spectators.contains(e.getPlayer().getName())) {
			e.setLeaveMessage(null);
		}
		
		
		/** Death **/
		
		if(Main.getState() == GameState.INGAME) Game.death(e.getPlayer());
	}
	
}
