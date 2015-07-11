package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.hologram.ArmorStandManager;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;

public class ListenerHolding implements Listener {
	
	/** Switch **/
	
	@EventHandler
	public void onSwitch(PlayerItemHeldEvent e) {
		/** Player **/
		
		Player p = e.getPlayer();

		
		/** Check state **/
		
		if(Main.getState() == GameState.INGAME) {
			if(!Game.spectators.contains(p.getName())) {
				if(IngameState.team.get(p.getName()).getFighter() == p) {
					ArmorStandManager.TeamArmorStands.get(p).setItemInHand(p.getItemInHand());
				}
			}
		}
	        
	}

}
