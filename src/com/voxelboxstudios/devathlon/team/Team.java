package com.voxelboxstudios.devathlon.team;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.voxelboxstudios.devathlon.state.IngameState;

public enum Team {
	
	/** Enum **/

	YELLOW, BLUE, GREEN, RED;

	
	/** Get color **/
	
	public Color getColor() {
		switch(this) {
			default:
				return Color.BLACK;
			case YELLOW:
				return Color.YELLOW;
			case BLUE:
				return Color.BLUE;
			case GREEN:
				return Color.GREEN;
			case RED:
				return Color.RED;
		}
	}
	
	public String getChatColor() {
		switch(this) {
			default:
				return "§7";
			case YELLOW:
				return "§e";
			case BLUE:
				return "§b";
			case GREEN:
				return "§a";
			case RED:
				return "§c";
		}
	}
	
	public Player getFighter() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(IngameState.arenas.contains(p)) {
				if(IngameState.team.get(p.getName()) == this) {
					return p;
				}
			}
		}
		return null;
	}
	
	public Player getBuilder() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!IngameState.arenas.contains(p)) {
				if(IngameState.team.get(p.getName()) == this) {
					if(!p.getGameMode().equals(GameMode.SPECTATOR)) {
						return p;
					}
				}
			}
		}
		return null;
	}
	
}
