package com.thedreamsanctuary.dreamindex.commands;

import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.thedreamsanctuary.dreamindex.SerializeData;


public class Dtlist implements CommandExecutor 
{
	SerializeData ser = new SerializeData();
	public boolean onCommand(CommandSender sender, Command arg, String arg2, String[] args) 
	{
		if (sender instanceof Player)
		{
			try
			{
				sender.sendMessage("List of tags");
				
				for (Entry<String, Integer> entry : ser.data().getTags().entrySet())
					sender.sendMessage(ChatColor.BLUE + entry.getKey() + " [" + entry.getValue() + "]");
				
				return true;
			} catch (Exception e)
			{
				
				sender.sendMessage(ChatColor.RED + "/dtlist     (Psst not sure how you messed this up");
			}
			
		}
		return false;
	}

}
