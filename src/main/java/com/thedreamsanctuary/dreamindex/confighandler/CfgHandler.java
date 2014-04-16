package com.thedreamsanctuary.dreamindex.confighandler;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;



public class CfgHandler
{

	private YamlConfiguration  mainCfg= new YamlConfiguration();
	private YamlConfiguration  descript= new YamlConfiguration();
	private File dataFolder;
	//DreamIndex dr = new DreamIndex();
	Set<String> whisperList = new HashSet<String>();

	public void setDataFolder(File dFolder)
	{
		this.dataFolder = dFolder;
	}
	
	
	public void loadFile()
	{
		File cfgFile = new File(dataFolder + File.separator + "config.yml");
		
		try
		{
			if (!cfgFile.exists())
			{
				mainCfg.set("Config.TagCreationLimit", 8);
				mainCfg.set("Config.TagWearLimit", 8);
				mainCfg.set("Config.descriptionLimit", 50);
				mainCfg.save(cfgFile);
			}
			else
				mainCfg.load(cfgFile);
			
				
				
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public YamlConfiguration loadDescript()
	{
		YamlConfiguration yamlCfg = new YamlConfiguration();
		File descFile = new File(dataFolder + File.separator + "description.yml");
		
		try
		{
			if (!descFile.exists())
			{
				descript.createSection("Description:");
				descript.save(descFile);
			}
			else
			{
				descript.load(descFile);
				return yamlCfg;
				
			}		
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return yamlCfg;
	}
	/*======================
	 * 		Config
	 ======================*/
	public boolean getTagLimit(int size)
	{
		loadFile();
		int limit =  mainCfg.getInt("Config.TagCreationLimit");
		if (limit <= 0)
		{
			return false;
		}
		else if (limit <= size)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean getTagWearLimit(int size)
	{
		loadFile();
		int limit = 0;
		System.out.println("SIZE IS" + size + " THE LIMIT IS" + mainCfg.getInt("Config.TagWearLimit"));
		limit = mainCfg.getInt("Config.TagWearLimit");
		
		if (limit <= 0)
		{
			return false;
		}
		else if (limit <= size)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean getDescriptLimit(int size)
	{
		loadFile();
		int limit = mainCfg.getInt("Config.TagCreationLimit");
		if (limit <= 0)
		{
			return false;
		}
		else if (limit <= size)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setPlayerDescript(Player sender, String description)
	{
		loadDescript().set("Description." + sender.getName(), sender);
	}
	public String getPlayerDescript(Player sender)
	{
		loadDescript().get("Description." + sender.getName());
		return null;
		
	}
	
}
