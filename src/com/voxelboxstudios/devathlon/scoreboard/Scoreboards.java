package com.voxelboxstudios.devathlon.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.LobbyState;
import com.voxelboxstudios.devathlon.team.Team;

public class Scoreboards {
	
	/** Update **/
	
	public static void update(Player p) {
		/** Lobby **/
		
		if(Main.getState() == GameState.LOBBY) {
			/** Scoreboard **/
			
			Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
			
			
			/** Objective **/
			
			Objective obj = sb.registerNewObjective("lobby", "dummy");
			
			
			/** Set slot **/
			
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			
			/** Set display name **/
			
			obj.setDisplayName("§8» §eLobby");
			
			
			/** Set score **/
			
			obj.getScore("§eKarte:").setScore(4);
			obj.getScore("§7" + Main.getMap().getName()).setScore(3);
			obj.getScore(" ").setScore(2);
			obj.getScore("§eBenötigte Spieler:").setScore(1);
			obj.getScore("§7" + LobbyState.getMinimalPlayers()).setScore(0);
			
			
			/** Set scoreboard **/
			
			p.setScoreboard(sb);
		} else {
			/** Scoreboard **/
			
			Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
			
			
			/** Objective **/
			
			Objective obj = sb.registerNewObjective("lobby", "dummy");
			
			
			/** Set slot **/
			
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			
			/** Set display name **/
			
			obj.setDisplayName("§8» §ePunkte");
			
			
			/** Set score **/
			
			obj.getScore("§eGelb:").setScore(Game.points.get(Team.YELLOW));
			obj.getScore("§cRot:").setScore(Game.points.get(Team.RED));
			obj.getScore("§aGrün:").setScore(Game.points.get(Team.GREEN));
			obj.getScore("§bBlau:").setScore(Game.points.get(Team.BLUE));
			
			
			/** Set scoreboard **/
			
			p.setScoreboard(sb);
		}
	}

}
