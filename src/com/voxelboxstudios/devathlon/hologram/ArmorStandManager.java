package com.voxelboxstudios.devathlon.hologram;
 
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.voxelboxstudios.devathlon.team.Team;
 
public class ArmorStandManager {
       
public static HashMap<Team, ArmorStand> TeamArmorStands = new HashMap<>();
	
	public static void spawnArmorStand(Location loc, Team team) {
               
		ArmorStand as = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        
		if(team.getFighter() == null) {
			as.setCustomName(team.getChatColor() + "Team inaktiv");
		} else {
			as.setCustomName(team.getChatColor() + team.getFighter().getName());
		}
		
		as.setCustomNameVisible(true);
		as.setGravity(false);
		as.setArms(true);
		as.setBasePlate(false);
		TeamArmorStands.put(team, as);
		
	}
        
	public static void changeArmorStandItem(Team team, ItemStack Hand) {
        	
		TeamArmorStands.get(team).setItemInHand(Hand);
        	
	}
	
	public static void changeArmorStandArmor(Team team, ItemStack Head, ItemStack Body, ItemStack Legs, ItemStack Feet) {
    	
		ArmorStand as = TeamArmorStands.get(team);
		as.setHelmet(Head);
		as.setChestplate(Body);
		as.setLeggings(Legs);
		as.setBoots(Feet);
        	
	}
       
}