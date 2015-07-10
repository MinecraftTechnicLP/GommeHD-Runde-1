package com.voxelboxstudios.devathlon.team;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import com.voxelboxstudios.devathlon.items.ItemUtil;

public class TeamSelection {

	/** Inventory **/
	
	private static Inventory inventory;
	
	
	/** Setup **/
	
	public static void setup() {
		/** Create inventory **/
		
		inventory = Bukkit.createInventory(null, 9, "§lTeam Auswahl");
		
		
		/** Set items **/
		
		inventory.setItem(1, ItemUtil.getLeatherItemStack(Material.LEATHER_HELMET, "§eGelbes Team", (short) 0, Color.YELLOW, new ArrayList<String>()));
		inventory.setItem(3, ItemUtil.getLeatherItemStack(Material.LEATHER_HELMET, "§bBlaues Team", (short) 0, Color.BLUE, new ArrayList<String>()));
		inventory.setItem(5, ItemUtil.getLeatherItemStack(Material.LEATHER_HELMET, "§aGrünes Team", (short) 0, Color.GREEN, new ArrayList<String>()));
		inventory.setItem(7, ItemUtil.getLeatherItemStack(Material.LEATHER_HELMET, "§cRotes Team", (short) 0, Color.RED, new ArrayList<String>()));
	}


	/** Get inventory **/
	
	public static Inventory getInventory() {
		return inventory;
	}
	
}
