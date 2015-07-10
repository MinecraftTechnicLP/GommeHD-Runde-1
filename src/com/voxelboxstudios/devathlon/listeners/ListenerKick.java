package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class ListenerKick implements Listener {

	/** Kick **/
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		e.setLeaveMessage("§8» §6" + e.getPlayer().getName() + " §7hat das Spiel betreten §8[" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + "].");
	}
	
}
