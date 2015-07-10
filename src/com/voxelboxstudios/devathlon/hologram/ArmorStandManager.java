package com.voxelboxstudios.devathlon.hologram;
 
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.voxelboxstudios.devathlon.team.Team;
 
public class ArmorStandManager {
       
	public static HashMap<Team, EntityArmorStand> TeamArmorStands = new HashMap<>();
	
	public static void spawnArmorStand(Location loc, Team team) {
               
		EntityArmorStand as = new EntityArmorStand(((CraftWorld) loc.getWorld()).getHandle());
        	
		as.setCustomName(team.getFighter().getName());
		as.setCustomNameVisible(true);
		as.setGravity(false);
		as.setArms(true);
		as.setBasePlate(false);
            
		PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(as);
		
		for(Player t : team.getBuilders())
			((CraftPlayer) t).getHandle().playerConnection.sendPacket(packet);
	}
        
	public static void changeArmorStandItem(Team team, ItemStack Hand) {
        	
		EntityArmorStand a = TeamArmorStands.get(team);
		ArmorStand as = (ArmorStand) a.getBukkitEntity();
		
		as.setItemInHand(Hand);
        	
	}
	
	public static void changeArmorStandArmor(Team team, ItemStack Head, ItemStack Body, ItemStack Legs, ItemStack Feet) {
    	
		EntityArmorStand a = TeamArmorStands.get(team);
		ArmorStand as = (ArmorStand) a.getBukkitEntity();
		
		as.setHelmet(Head);
		as.setChestplate(Body);
		as.setLeggings(Legs);
		as.setBoots(Feet);
        	
	}
       
}