package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ListenerQuit implements Listener {

	/** Quit **/
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage("§8» §6" + e.getPlayer().getName() + " §7hat das Spiel verlassens §8[" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers() + "].");
	}
	
}
