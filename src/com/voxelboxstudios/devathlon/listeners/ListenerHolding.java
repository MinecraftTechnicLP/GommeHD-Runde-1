package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.voxelboxstudios.devathlon.hologram.ArmorStandManager;
import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerHolding {
	
	@EventHandler
	public void onSwitch(PlayerItemHeldEvent e) {
		
	        Player p = e.getPlayer();
	        
	        ArmorStandManager.changeArmorStandItem(IngameState.team.get(p), p.getInventory().getItem(e.getNewSlot()));
	        
	}

}
