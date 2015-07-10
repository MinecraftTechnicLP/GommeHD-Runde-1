package com.voxelboxstudios.devathlon.listeners;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.voxelboxstudios.devathlon.Game;
import com.voxelboxstudios.devathlon.Main;
import com.voxelboxstudios.devathlon.mysql.SQL;
import com.voxelboxstudios.devathlon.scoreboard.Scoreboards;
import com.voxelboxstudios.devathlon.state.IngameState;
import com.voxelboxstudios.devathlon.stats.Stats;
import com.voxelboxstudios.devathlon.team.Team;

import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class ListenerDeath implements Listener {

	/** Death **/
	
	@EventHandler
	public void onDeath(final PlayerDeathEvent e) {
		/** Keep inventory **/
		
		e.setKeepInventory(true);
		
		
		/** Death message **/
		
		e.setDeathMessage(null);
		
		
		/** Auto respawn **/
		
		new BukkitRunnable() {
			  @Override
			  public void run() {
				  PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
				  ((CraftPlayer) e.getEntity()).getHandle().playerConnection.a(packet);
			  }
		}.runTaskLater(Main.getPlugin(), 1L);
			
			
		/** Lightning effect **/
		
		Main.getMap().getSpectatorSpawn().getWorld().strikeLightningEffect(Main.getMap().getSpectatorSpawn());
		
		
		/** Death **/
		
		Game.death(e.getEntity());
		
		/** Stats **/
		
		new BukkitRunnable() {
			public void run() {
				/** Update **/
				
				try {
					SQL.getDatabase().queryUpdate("UPDATE " + SQL.prefix + "stats SET deaths = deaths+1 WHERE uuid='" + e.getEntity().getUniqueId().toString() + "'");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				/** Load stats **/
				
				Stats.load(e.getEntity());
			}
		}.runTaskAsynchronously(Main.getPlugin());
		
		
		/** Check if killer isn't null **/
		
		if(e.getEntity().getKiller() != null) {
			/** Random kill message **/
			
			List<String> death_messages = new ArrayList<String>();
			
			
			/** Add kill messages **/
			
			death_messages.add("erledigt");
			death_messages.add("getötet");
			death_messages.add("pulverisiert");
			death_messages.add("zermalmt");
			
			
			/** Shuffle death messages **/
			
			Collections.shuffle(death_messages);
			
			
			/** Set death message **/
			
			Bukkit.broadcastMessage("§8» " + IngameState.team.get(e.getEntity().getName()).getChatColor() + e.getEntity().getName() + " §7wurde von "+IngameState.team.get(e.getEntity().getKiller().getName()).getChatColor() + e.getEntity().getKiller().getName() + "§7 " + death_messages.get(0) + ".");
			
			
			/** Check if killer is in a team **/
			
			if(IngameState.arenas.contains(e.getEntity().getKiller())) {
				/** Team **/
				
				Team team = IngameState.team.get(e.getEntity().getKiller().getName());
				
				/** Add Point **/
				
				Game.points.put(team, Game.points.get(team) + 1);
				
				
				/** Stats **/
				
				new BukkitRunnable() {
					public void run() {
						/** Update **/
						
						try {
							SQL.getDatabase().queryUpdate("UPDATE " + SQL.prefix + "stats SET kills = kills+1 WHERE uuid='" + e.getEntity().getKiller().getUniqueId().toString() + "'");
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						
						/** Load stats **/
						
						Stats.load(e.getEntity().getKiller());
					}
				}.runTaskAsynchronously(Main.getPlugin());
				
				
				/** Loop trough players **/
				
				for(Player p : Bukkit.getOnlinePlayers()) {
					/** Scoreboard **/
					
					Scoreboards.update(p);
					
					
					/** Potions **/
					
					if(!Game.spectators.contains(p.getName())) {
						if(p != e.getEntity().getKiller() && IngameState.team.get(p.getName()) == team) {
							/** Add potion effect **/
							
							p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 60, 2));
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60, 1));
							
							
							/** Play sound **/
							
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
						}	
					}
				}
			}
		} else {
			/** Set death message **/
			
			Bukkit.broadcastMessage("§8»" + IngameState.team.get(e.getEntity().getName()).getChatColor() + e.getEntity().getName() + " §7ist gestorben!");	
		}
	}
	
}
