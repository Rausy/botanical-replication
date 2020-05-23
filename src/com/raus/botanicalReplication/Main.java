package com.raus.botanicalReplication;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	private boolean excludeWitherRose;

	@Override
	public void onEnable()
	{
		// Config stuff
		saveDefaultConfig();
		reload();

		// Listeners
		getServer().getPluginManager().registerEvents(new BoneMealListener(), this);

		// Register command
		this.getCommand("botanical").setExecutor(new ReloadCommand());
	}

	@Override
	public void onDisable()
	{

	}

	public void reload()
	{
		excludeWitherRose = getConfig().getBoolean("excludeWitherRose");
	}

	public boolean excludeWitherRose()
	{
		return excludeWitherRose;
	}
}