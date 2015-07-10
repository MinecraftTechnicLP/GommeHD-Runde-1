package com.voxelboxstudios.devathlon.commands;

import java.text.DecimalFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.voxelboxstudios.devathlon.stats.Stats;

public class CommandMap implements CommandExecutor {

	/** Command **/
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		/** Player **/
		
		Player p = (Player) sender;
		
		
		/** Stats **/
		
		Stats stats = Stats.getStats(p);
		
		if(stats != null) {
			/** Send stats **/
			
			p.sendMessage("§6§m---------------------------------");
			p.sendMessage("§8» §7Deine Kills: §e" + stats.getKills());
			p.sendMessage("§8» §7Deine Deaths: §e" + stats.getDeaths());
			p.sendMessage("§8» §7Deine gespielten Spiele: §e" + stats.getGamesPlayed());
			p.sendMessage("§8» §7Deine gewonnenen Spiele: §e" + stats.getWins());
			p.sendMessage("§8» §7Deine K/D: §e" + new DecimalFormat("#.##").format(stats.getKD()));
			p.sendMessage("§8» §7Deine Gewinnchance: §e" + stats.getWinChance() + "%");
			p.sendMessage("§6§m---------------------------------");
		} else {
			/** Send stats **/
			
			p.sendMessage("§8» §7Deine Stats werden noch geladen.");
		}
		
		
		/** Return true **/
		
		return true;
	}

}
