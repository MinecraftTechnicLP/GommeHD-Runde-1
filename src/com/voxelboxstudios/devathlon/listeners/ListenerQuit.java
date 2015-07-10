package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerQuit implements Listener {

	/** Quit **/
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {	 
		/** Quit message **/
		
		if(Main.getState() == GameState.LOBBY) e.setQuitMessage("§8» §6" + e.getPlayer().getName() + " §7hat das Spiel verlassen §8[" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + "]");
		else
			e.setQuitMessage("§8» " + IngameState.team.get(e.getPlayer().getName()).getChatColor() + e.getPlayer().getName() + " §7hat das Spiel verlassen.");
		
		
		/** Spectators **/
		
		if(Game.spectators.contains(e.getPlayer().getName())) {
			e.setQuitMessage(null);
		}
		
		
		/** Death **/
		
		if(Main.getState() == GameState.INGAME) Game.death(e.getPlayer());
	}
	
}
