package com.voxelboxstudios.devathlon.mysql;

import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

import com.voxelboxstudios.devathlon.Main;

public class SQL {

	/** Database **/
	
	private static Database db;
	
	
	/** Prefix **/
	
	public static String prefix;
	
	
	/** Connect **/
	
	public static void connect() throws ClassNotFoundException, SQLException {
		/** Get file configuration from main class **/
		
		FileConfiguration cfg = Main.getPlugin().getConfig();
		
		
		/** Prefix **/
		
		prefix = cfg.getString("mysql.tableprefix");
		
		
		/** Setup database **/
		
		db = new Database(
				cfg.getString("mysql.host"), 
				cfg.getInt("mysql.port"), 
				cfg.getString("mysql.database"), 
				cfg.getString("mysql.username"), 
				cfg.getString("mysql.password"), 
				true); /** Auto reconnect **/
		
		
		/** Open connection **/
		
		db.openConnection();
		
		
		/** Create tables **/
		
		db.queryUpdate("CREATE TABLE IF NOT EXISTS " + prefix + "stats (uuid VARCHAR(100), kills INT, deaths INT, wins INT, gamesplayed INT)");
		db.queryUpdate("CREATE TABLE IF NOT EXISTS " + prefix + "maps (id int AUTO_INCREMENT primary key NOT NULL, name VARCHAR(100), blue VARCHAR(100), red VARCHAR(100), green VARCHAR(100), yellow VARCHAR(100), spectator VARCHAR(100), blueoutside VARCHAR(100), redoutside VARCHAR(100), greenoutside VARCHAR(100), yellowoutside VARCHAR(100), shop VARCHAR(100))");
	}
	
	
	/** Get database  **/
	
	public static Database getDatabase() {
		return db;
	}
	
}