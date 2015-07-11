package com.voxelboxstudios.devathlon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import com.voxelboxstudios.devathlon.hologram.ArmorStandManager;
import com.voxelboxstudios.devathlon.items.ItemUtil;
import com.voxelboxstudios.devathlon.mysql.SQL;
import com.voxelboxstudios.devathlon.state.IngameState;
import com.voxelboxstudios.devathlon.stats.Stats;
import com.voxelboxstudios.devathlon.team.Team;

public class Game {

	/** Cooldown **/
	
	public static Map<String, Long> cooldown = new HashMap<String, Long>();
	
	
	/** Points **/
	
	public static Map<Team, Integer> points = new HashMap<Team, Integer>();
	
	
	/** Spectators **/
	
	public static List<String> spectators = new ArrayList<String>();
	
	
	/** Respawn **/
	
	public static Map<String, Location> respawn = new HashMap<String, Location>();


	/** Won **/
	
	public static boolean won = false;

	
	/** Death **/
	
	public static void death(Player p) {
		/** Check win **/
		
		if(checkWin()) return;
		
			
		/** Check if player was arena player **/
		
		if(IngameState.arenas.contains(p)) {
			/** Team **/
			
			Team t = IngameState.team.get(p.getName());
			
			
			/** Check builders size **/
			
			if(t.getBuilders().size() > 0) {
				/** New builder **/
				
				List<Player> builders = new ArrayList<Player>(t.getBuilders());
				
				
				/** Shuffle builders **/
				
				Collections.shuffle(builders);
				
				
				/** New builder **/
				
				Player b = builders.get(0);
				
				
				/** Add to arena **/
				
				IngameState.arenas.add(b);
				
				
				/** Remove player **/
				
				IngameState.arenas.remove(p);
				
				
				/** Send messages **/
				
				p.sendMessage("§6§m---------------------------------");
				p.sendMessage("§8» §7Du bist nun ein Sammler.");
				p.sendMessage("§8» §7Du musst nun selbst Materialien sammeln.");
				p.sendMessage("§6§m---------------------------------");
				
				b.sendMessage("§6§m---------------------------------");
				b.sendMessage("§8» §7Du bist nun der neue §6§lKämpfer§r§7.");
				b.sendMessage("§8» §7Du musst nun mit den vorhandenen Materialien kämpfen!");
				b.sendMessage("§6§m---------------------------------");
				
				
				/** ArmorStand **/
				
				ArmorStandManager.TeamArmorStands.get(IngameState.team.get(p.getName())).setCustomName(IngameState.team.get(p.getName()).getChatColor() + b.getName());
				
				
				/** Fire ticks **/
				
				b.setFireTicks(0);
				
				
				/** Level **/
				
				b.setLevel(p.getLevel());
				b.setExp(p.getExp());
				
				p.setLevel(0);
				p.setExp(0f);
				
				
				/** Teleport **/
				
				respawn.put(p.getName(), b.getLocation());
				
				
				/** Cursors **/
				
				if(b.getItemOnCursor() != null) b.getInventory().addItem(b.getItemOnCursor());
				if(p.getItemOnCursor() != null) p.getInventory().addItem(p.getItemOnCursor());
				
				b.setItemOnCursor(null);
				p.setItemOnCursor(null);
				
				
				/** Cooldown **/
				
				cooldown.put(b.getName(), System.currentTimeMillis());
				
				
				/** Potions **/
				
				for(PotionEffect pe : b.getActivePotionEffects()) {
					b.removePotionEffect(pe.getType());
				}
				
				
				/** Teleport **/
				
				b.teleport(Main.getMap().getPositions().get(t));
				
				
				/** Change inventory **/
				
				ItemStack[] contents = p.getInventory().getContents();
				ItemStack[] armor = p.getInventory().getArmorContents();
				
				p.getInventory().setContents(b.getInventory().getContents());
				p.getInventory().setArmorContents(b.getInventory().getArmorContents());
				
				
				/** Clear inventory **/
				
				b.getInventory().setContents(contents);
				b.getInventory().setArmorContents(armor);
				
				
				/** Set gamemode **/
				
				p.setGameMode(GameMode.SURVIVAL);
			}
		}
	}
	
	
	/** Check win **/
	
	public static boolean checkWin() {
		/** Won **/
		
		if(won) return true;
		
		
		/** Loop through teams **/
		
		for(Team t : Team.values()) {
			if(points.get(t) == Main.getWinningPoints()) {
				/** Won **/
				
				won = true;
				
				
				/** Loop **/
				
				for(final Player p : Bukkit.getOnlinePlayers()) {
					/** Set level **/
					
					p.setLevel(0);
					p.setExp(0f);
					
					
					/** Set health **/
					
					p.setHealth(20.0D);
					
					
					/** Set fire ticks **/
					
					p.setFireTicks(0);
					
					
					/** Potions **/
					
					for(PotionEffect pe : p.getActivePotionEffects()) {
						p.removePotionEffect(pe.getType());
					}
					
					
					/** Inventory **/
					
					p.getInventory().clear();
					p.getInventory().setArmorContents(null);
					
					
					/** Clear chat **/
					
					for(int i = 0; i < 100; i++) p.sendMessage(" ");
					
					
					/** Play sound **/
					
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 3);
					
					
					/** Spawn firework **/
					
					new BukkitRunnable() {
						public void run() {
							spawnFirework(p.getLocation());
						}
					}.runTaskTimer(Main.getPlugin(), 0L, 20L);
				}

				
				/** Broadcast **/
				
				Bukkit.broadcastMessage(Main.prefix + "Das Spiel ist vorbei! Gewonnen hat/haben:");
				
				String list = "";
				
				for(final Player tt : Bukkit.getOnlinePlayers()) {
					if(!spectators.contains(tt.getName())) {
						if(IngameState.team.get(tt.getName()) == t) {
							/** Stats **/
							
							new BukkitRunnable() {
								public void run() {
									/** Update **/
									
									try {
										SQL.getDatabase().queryUpdate("UPDATE " + SQL.prefix + "stats SET wins = wins+1 WHERE uuid='" + tt.getUniqueId().toString() + "'");
									} catch (SQLException e) {
										e.printStackTrace();
									}
									
									
									/** Load stats **/
									
									Stats.load(tt);
								}
							}.runTaskAsynchronously(Main.getPlugin());
							
							
							/** Add to list **/
							
							list = list + ", " + tt.getName();
						}
					}
				}
				
				list = list.replaceFirst(", ", "");
				
				Bukkit.broadcastMessage("§e" + list);
				
				
				/** Scheduler **/
				
				new BukkitRunnable() {
					public void run() {
						Bukkit.shutdown();
					}
				}.runTaskLater(Main.getPlugin(), 5 * 20L);
				
				
				/** Return true **/
				
				return true;
			}
		}
		
		
		/** Return false **/
		
		return false;
	}
	
	
	/** Builder inventory **/
	
	public static void builderInventory(Player p) {
		/** Items **/
		
		p.getInventory().addItem(ItemUtil.getItemStack(Material.WOOD_PICKAXE, "§eSpitzhacke", (short) 0, null));
		p.getInventory().addItem(ItemUtil.getItemStack(Material.WOOD_AXE, "§eAxt", (short) 0, null));
	}
	
	
	/** Spawn random firework **/
	
	public static void spawnFirework(Location l) {
        Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        Random r = new Random();  

        int rt = r.nextInt(4) + 1;
        Type type = Type.BALL;      
        if (rt == 1) type = Type.BALL;
        if (rt == 2) type = Type.BALL_LARGE;
        if (rt == 3) type = Type.BURST;
        if (rt == 4) type = Type.CREEPER;
        if (rt == 5) type = Type.STAR;
       
        int r1i = r.nextInt(17) + 1;
        int r2i = r.nextInt(17) + 1;
        Color c1 = getColor(r1i);
        Color c2 = getColor(r2i);

        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fwm.addEffect(effect);
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);
        fw.setFireworkMeta(fwm);  
	}
	
	
	/** Get random color **/
	
	private static Color getColor(int i) {
		Color c = null;
		if(i==1){
			c=Color.AQUA;
		}
		if(i==2){
			c=Color.BLACK;
		}
		if(i==3){
			c=Color.BLUE;
		}
		if(i==4){
			c=Color.FUCHSIA;
		}
		if(i==5){
			c=Color.GRAY;
		}
		if(i==6){
			c=Color.GREEN;
		}
		if(i==7){
			c=Color.LIME;
		}
		if(i==8){
			c=Color.MAROON;
		}
		if(i==9){
			c=Color.NAVY;
		}
		if(i==10){
			c=Color.OLIVE;
		}
		if(i==11){
			c=Color.ORANGE;
		}
		if(i==12){
			c=Color.PURPLE;
		}
		if(i==13){
			c=Color.RED;
		}
		if(i==14){
			c=Color.SILVER;
		}
		if(i==15){
			c=Color.TEAL;
		}
		if(i==16){
			c=Color.WHITE;
		}
		if(i==17){
			c=Color.YELLOW;
		}
		 
		return c;
	}
	
}
