package com.voxelboxstudios.devathlon.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

	/** Short item stack **/
	
	public static ItemStack getItemStack(Material material, String name) {
		/** ItemStack **/
		
		ItemStack is = new ItemStack(material, 1);
		
		
		/** Item meta **/
		
		ItemMeta im = is.getItemMeta();
		
		
		/** Set display name **/
		
		im.setDisplayName(name);
		
		
		/** Set item meta **/
		
		is.setItemMeta(im);
		
		
		/** Return item **/
		
		return is;
	}
	
}
