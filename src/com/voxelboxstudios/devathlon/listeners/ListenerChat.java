package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;
import com.voxelboxstudios.devathlon.team.Team;

public class ListenerChat implements Listener {

	/** Chat **/
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		/** Spectator **/
		
		if(Game.spectators.contains(e.getPlayer().getName())) {
			/** Format **/
			
			e.setFormat("§8§o[Zuschauer] §7%s: %s");
			
			
			/** Recipents **/
			
			e.getRecipients().clear();
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(Game.spectators.contains(p.getName())) {
					e.getRecipients().add(p);
				}
			}
			
			
			/** Return **/
			
			return;
		}
		
		
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
