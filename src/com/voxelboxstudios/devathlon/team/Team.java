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

	YELLOW(Color.YELLOW, "§e"),
	BLUE(Color.BLUE, "§b"),
	GREEN(Color.GREEN, "§a"),
	RED(Color.RED, "§c");

	private Color c;
	private String cc;
	
	Team(Color c, String cc) {
		this.c = c;
		this.cc = cc;
	}
	
	/** Get color **/
	
	public Color getColor() {
		return c;
	}
	
	
	/** Get chat color **/
	
	public String getChatColor() {
		return cc;
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
