package com.voxelboxstudios.devathlon.team;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.voxelboxstudios.devathlon.Game;
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
	
	
	/** Get chat color **/
	
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
	
	
	/** Get fighter **/
	
	public Player getFighter() {
		/** Loop **/
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(IngameState.arenas.contains(p)) {
				if(IngameState.team.get(p.getName()) == this) {
					return p;
				}
			}
		}
		
		
		/** Return null **/
		
		return null;
	}
	
	
	/** Get builders **/
	
	public List<Player> getBuilders() {
		/** Builders **/
		
		List<Player> builders = new ArrayList<Player>();
		
		
		/** Loop **/
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!IngameState.arenas.contains(p) && !Game.spectators.contains(p.getName())) {
				if(IngameState.team.get(p.getName()) == this) {
					builders.add(p);
				}
			}
		}
		
		
		/** Return builders **/
		
		return builders;
	}
}
