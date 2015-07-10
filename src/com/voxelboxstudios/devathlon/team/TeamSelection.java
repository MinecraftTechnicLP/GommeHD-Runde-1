package com.voxelboxstudios.devathlon.team;

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
		
		inventory.setItem(1, ItemUtil.getLeatherItemStack(Material.LEATHER_HELMET, "§eGelbes Team", (short) 0, Color.YELLOW));
		inventory.setItem(3, ItemUtil.getLeatherItemStack(Material.LEATHER_HELMET, "§bBlaues Team", (short) 0, Color.BLUE));
		inventory.setItem(5, ItemUtil.getLeatherItemStack(Material.LEATHER_HELMET, "§aGrünes Team", (short) 0, Color.GREEN));
		inventory.setItem(7, ItemUtil.getLeatherItemStack(Material.LEATHER_HELMET, "§cRotes Team", (short) 0, Color.RED));
	}


	/** Get inventory **/
	
	public static Inventory getInventory() {
		return inventory;
	}
	
}
