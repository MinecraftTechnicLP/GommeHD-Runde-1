package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;
import com.voxelboxstudios.devathlon.team.Team;

public class ListenerChat implements Listener {

	/** Chat **/
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		/** Lobby **/
		
		if(Main.getState() == GameState.LOBBY) {
			e.setFormat("§7%s: §f%s");
		} else {
			/** Team **/
			
			Team t = IngameState.team.get(e.getPlayer().getName());
			
			
			/** Prefix **/
			
			String prefix = "";
			
			if(IngameState.arenas.contains(e.getPlayer())) {
				prefix = "§6§l§oKämpfer §r";
			}
			
			
			/** Set format **/
			
			e.setFormat(prefix + t.getChatColor() + "%s§7: §f%s");
		}
	}
	
}
