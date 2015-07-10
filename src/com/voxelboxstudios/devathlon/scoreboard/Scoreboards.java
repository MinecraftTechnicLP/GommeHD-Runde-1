package com.voxelboxstudios.devathlon.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.IngameState;
import com.voxelboxstudios.devathlon.state.LobbyState;

public class Scoreboards {
	
	/** Update **/
	
	@SuppressWarnings("deprecation")
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
			
			obj.getScore("§eGelb:").setScore(Game.points.get(com.voxelboxstudios.devathlon.team.Team.YELLOW));
			obj.getScore("§cRot:").setScore(Game.points.get(com.voxelboxstudios.devathlon.team.Team.RED));
			obj.getScore("§aGrün:").setScore(Game.points.get(com.voxelboxstudios.devathlon.team.Team.GREEN));
			obj.getScore("§bBlau:").setScore(Game.points.get(com.voxelboxstudios.devathlon.team.Team.BLUE));
			
			
			/** Teams **/
			
			Team red = sb.registerNewTeam("red");
			Team green = sb.registerNewTeam("green");
			Team blue = sb.registerNewTeam("blue");
			Team yellow = sb.registerNewTeam("yellow");
			
			red.setAllowFriendlyFire(false);
			red.setCanSeeFriendlyInvisibles(true);
			red.setPrefix(com.voxelboxstudios.devathlon.team.Team.RED.getChatColor());
			
			blue.setAllowFriendlyFire(false);
			blue.setCanSeeFriendlyInvisibles(true);
			blue.setPrefix(com.voxelboxstudios.devathlon.team.Team.BLUE.getChatColor());
			
			green.setAllowFriendlyFire(false);
			green.setCanSeeFriendlyInvisibles(true);
			green.setPrefix(com.voxelboxstudios.devathlon.team.Team.GREEN.getChatColor());
			
			yellow.setAllowFriendlyFire(false);
			yellow.setCanSeeFriendlyInvisibles(true);
			yellow.setPrefix(com.voxelboxstudios.devathlon.team.Team.YELLOW.getChatColor());
			
			
			/** Add players to teams **/
			
			for(Player t : Bukkit.getOnlinePlayers()) {
				if(!Game.spectators.contains(t.getName())) {
					com.voxelboxstudios.devathlon.team.Team te = IngameState.team.get(t.getName());
					
					if(te == com.voxelboxstudios.devathlon.team.Team.GREEN) green.addPlayer(t);
					if(te == com.voxelboxstudios.devathlon.team.Team.RED) red.addPlayer(t);
					if(te == com.voxelboxstudios.devathlon.team.Team.BLUE) blue.addPlayer(t);
					if(te == com.voxelboxstudios.devathlon.team.Team.YELLOW) yellow.addPlayer(t);
				}
			}
			
			
			/** Set scoreboard **/
			
			p.setScoreboard(sb);
		}
	}

}
