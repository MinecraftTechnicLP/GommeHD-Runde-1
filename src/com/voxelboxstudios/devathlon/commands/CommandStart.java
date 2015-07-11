package com.voxelboxstudios.devathlon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.state.GameState;
import com.voxelboxstudios.devathlon.state.LobbyState;

public class CommandStart implements CommandExecutor {

	/** Command **/
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdlabel, String[] args) {
		/** Player **/
		
		Player p = (Player) sender;
		
		
		/** OP **/
		
		if(!p.isOp()) {
			/** Send message **/
			
			p.sendMessage(Main.prefix + "Du hast keine Erlaubnis dies zu tun.");
			
			
			/** Return **/
			
			return true;
		}
		
		
		/** Check state **/
		
		if(Main.getState() == GameState.LOBBY) {
			LobbyState.setCurrentTime(8);
		} else {
			/** Send message **/
			
			p.sendMessage(Main.prefix + "Das Spiel hat bereits begonnen.");
		}
		
		
		/** Return true **/
		
		return true;
	}

}
