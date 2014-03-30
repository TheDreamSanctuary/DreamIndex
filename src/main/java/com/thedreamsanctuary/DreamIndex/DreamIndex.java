package com.thedreamsanctuary.DreamIndex;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

import com.thedreamsanctuary.DreamIndex.OnCommand;

public class DreamIndex extends JavaPlugin
{
	public static File pluginLoc = null;
	
	@Override
	public void onDisable() 
	{
	  
	}
	@Override
	public void onEnable()
	{
		pluginLoc = this.getDataFolder();
		
		OnCommand onCommand = new OnCommand();
		getServer().getPluginCommand("di").setExecutor(onCommand);

		//getServer().getPluginCommand("slmkdir").setExecutor(new OnCommand());

		//this.getServer().getPluginManager().registerEvents(this, this);
	}
}
