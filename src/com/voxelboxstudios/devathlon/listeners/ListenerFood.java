package com.voxelboxstudios.devathlon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class ListenerFood implements Listener {

	/** Food level change **/
	
	@EventHandler
	public void onChange(FoodLevelChangeEvent e) {
		e.setFoodLevel(20);
	}
	
}
