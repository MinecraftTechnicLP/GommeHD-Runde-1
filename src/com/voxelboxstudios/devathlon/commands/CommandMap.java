package com.voxelboxstudios.devathlon.commands;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.mysql.SQL;

public class CommandMap implements CommandExecutor {

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
		
		
		/** No arguments **/
		
		if(args.length == 0) {
			p.sendMessage("§6§m---------------------------------");
			p.sendMessage("§8» §7Map erstellen: §e/map create <Name>");
			p.sendMessage("§8» §7Map Liste: §e/map list");
			p.sendMessage("§8» §7Map Spawn setzen: §e/map setspawn <Name> <RED/GREEN/BLUE/YELLOW>");
			p.sendMessage("§8» §7Map Außenspawn setzen: §e/map setoutsidespawn <Name> <RED/GREEN/BLUE/YELLOW>");
			p.sendMessage("§8» §7Map Spectator setzen: §e/map setspectator <Name>");
			p.sendMessage("§6§m---------------------------------");
		}
		
		
		/** One argument **/
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("list")) {
				/** Send message **/
				
				p.sendMessage("§8» §7Folgende Maps sind aktiv:");
				
				
				/** Result **/
				
				ResultSet rs = null;
				
				try {
					rs = SQL.getDatabase().getQuery("SELECT * FROM " + SQL.prefix + "maps");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				/** Send maps **/
				
				try {
					while(rs.next()) {
						p.sendMessage("  §8» §e" + rs.getString(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		/** Multiple arguments **/
		
		if(args.length == 2) {
			/** Creation **/
			
			if(args[0].equalsIgnoreCase("create")) {
				/** Name **/
				
				String name = args[1];
				
				
				/** Insert **/
				
				try {
					SQL.getDatabase().queryUpdate("INSERT INTO " + SQL.prefix + "maps (name) VALUES (" + name + ")");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				/** Send message */
				
				p.sendMessage(Main.prefix + "Karte erstellt. Bitte konfiguriere diese, damit es nicht zu Fehlern kommt.");
			}
			
			
			/** Set spectator **/
			
			if(args[0].equalsIgnoreCase("setspectator")) {
				/** Name **/
				
				String name = args[1];
			
				
				/** Update **/
					
				try {
					SQL.getDatabase().queryUpdate("UPDATE " + SQL.prefix + "maps SET spectator='" + getLocation(p.getLocation()) + "' WHERE name='" + name + "'");
				} catch (SQLException e) {
					e.printStackTrace();
				}
					
					
				/** Send message */
					
				p.sendMessage(Main.prefix + "Zuschauer Änderung vorgenommen.");
			}
		}
		
		if(args.length == 3) {
			if(args[0].equalsIgnoreCase("setspawn") || args[0].equalsIgnoreCase("setoutsidespawn")) {
				/** Name **/
				
				String name = args[1];
				
				
				/** Team **/
				
				String team = args[2];
				
				
				/** Check team **/
				
				if(team.equalsIgnoreCase("red") 
						|| team.equalsIgnoreCase("green") 
						|| team.equalsIgnoreCase("blue") 
						|| team.equalsIgnoreCase("yellow")) {
					/** Team **/
					
					String t = args[0].equalsIgnoreCase("setspawn") ? team.toLowerCase() : team.toLowerCase() + "outside";
					
					
					/** Update **/
					
					try {
						SQL.getDatabase().queryUpdate("UPDATE " + SQL.prefix + "maps SET " + t + "='" + getLocation(p.getLocation()) + "' WHERE name='" + name + "'");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					
					/** Send message */
					
					p.sendMessage(Main.prefix + "Änderung vorgenommen.");
				} else {
					/** Send message */
					
					p.sendMessage(Main.prefix + "Bitte gebe ein gültiges Team an.");
				}
			}
		}
		
 		
		/** Return true **/
		
		return true;
	}

	
	/** Get location **/
	
	private String getLocation(Location location) {
		return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch();
	}

}
