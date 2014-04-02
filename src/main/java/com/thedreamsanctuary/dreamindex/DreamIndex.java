package com.thedreamsanctuary.dreamindex;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class DreamIndex extends JavaPlugin
{
	Data b = new Data();
	SerializeData ser = new SerializeData();
	
	@Override
	public void onDisable() 
	{
	  this.getLogger().log(Level.INFO, "Saving dreamIndex");
	  ser.serializeList();
	}
	@Override
	public void onEnable()
	{
		ser.setDataFolder(this.getDataFolder());
		ser.deserializeList();
	  
		OnCommand onCom = new OnCommand();
		getServer().getPluginCommand("dtcreate").setExecutor(onCom);
		getServer().getPluginCommand("dtrem").setExecutor(onCom);
		getServer().getPluginCommand("dtlist").setExecutor(onCom);
		getServer().getPluginCommand("duadd").setExecutor(onCom);
		getServer().getPluginCommand("durem").setExecutor(onCom);
		getServer().getPluginCommand("duinfo").setExecutor(onCom);
		getServer().getPluginCommand("dsearch").setExecutor(onCom);
	}
}
