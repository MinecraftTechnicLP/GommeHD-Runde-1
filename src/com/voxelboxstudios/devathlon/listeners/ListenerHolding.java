package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.hologram.ArmorStandManager;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerHolding implements Listener {
	
	@EventHandler
	public void onSwitch(PlayerItemHeldEvent e) {
		
		Player p = e.getPlayer();
	        
		if(Main.getState().equals(GameState.INGAME)) {
			ArmorStandManager.changeArmorStandItem(IngameState.team.get(p), p.getInventory().getItem(e.getNewSlot()));
		}
	        
	}

}
