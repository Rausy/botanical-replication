package com.raus.botanicalReplication;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BoneMealListener implements Listener
{
	private final Main plugin = JavaPlugin.getPlugin(Main.class);

	@EventHandler
	public void onBoneMeal(PlayerInteractEvent event)
	{
		// Get if player fertilizes a flower
		Block flower = event.getClickedBlock();
		ItemStack item = event.getItem();

		if (flower != null && Tag.SMALL_FLOWERS.isTagged(flower.getType()) &&
				item != null && item.getType() == Material.BONE_MEAL)
		{
			// Cancel
			if (plugin.excludeWitherRose() && flower.getType() == Material.WITHER_ROSE)
			{
				return;
			}

			// Spawn flower and bone meal effect
			World world = flower.getWorld();
			world.spawnParticle(Particle.VILLAGER_HAPPY, flower.getLocation().add(0.5, 0.5, 0.5), 15, 0.25, 0.25, 0.25, 0.015);
			world.dropItemNaturally(flower.getLocation(), new ItemStack(flower.getType()));

			// Use up one bone meal
			Player ply = event.getPlayer();
			if (ply.getGameMode() != GameMode.CREATIVE)
			{
				item.setAmount(item.getAmount() - 1);
			}

			// Swing arm
			if (event.getHand() == EquipmentSlot.HAND)
			{
				ply.swingMainHand();
			}
			else
			{
				ply.swingOffHand();
			}
		}
	}
}