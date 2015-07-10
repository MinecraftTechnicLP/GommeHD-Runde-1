package com.voxelboxstudios.devathlon.items;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemUtil {

	/** Short item stack **/
	
	public static ItemStack getItemStack(Material material, String name, short data, List<String> lore) {
		/** ItemStack **/
		
		ItemStack is = new ItemStack(material, 1, data);
		
		
		/** Item meta **/
		
		ItemMeta im = is.getItemMeta();
		
		
		/** Lore **/
		
		im.setLore(lore);
		
		
		/** Item flags **/
		
		im.addItemFlags(ItemFlag.values());
		
		
		/** Set display name **/
		
		im.setDisplayName(name);
		
		
		/** Set item meta **/
		
		is.setItemMeta(im);
		
		
		/** Return item **/
		
		return is;
	}

	public static ItemStack getLeatherItemStack(Material material, String name, short data, Color color, List<String> lore) {
		/** Item Stack **/
		
		ItemStack is = getItemStack(material, name, data, lore);
		
		
		/** Item meta **/
		
		LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
		
		
		/** Item flags **/
		
		meta.addItemFlags(ItemFlag.values());
		
		
		/** Set color **/
		
		meta.setColor(color);
		
		
		/** Set item meta **/
		
		is.setItemMeta(meta);
		
		
		/** Return item **/
		
		return is;
	}
	
}
