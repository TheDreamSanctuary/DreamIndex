package com.thedreamsanctuary.dreamindex.commands;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;
import com.thedreamsanctuary.dreamindex.SerializeData;

public class Dtrem implements CommandExecutor 
{
	SerializeData ser = new SerializeData();
	public boolean onCommand(CommandSender sender, Command comm, String str, String[] args) 
	{
		ArrayList<String> remList = new ArrayList<String>();
		
		Preconditions.checkArgument(sender instanceof Player, "Must be a player to execute this command");
		try
		{
			if (ser.data().getTags().containsKey(args[0]))
			{
				ser.data().getTags().remove(args[0]);
				
				for (Entry<String, String> entry : ser.data().getPlayerMap().entries())
				{
					if (entry.getValue().equalsIgnoreCase(args[0]))
					{
						remList.add(entry.getKey());
					}
				}
				for (String s : remList)
					ser.data().getPlayerMap().remove(s, args[0]);
				
				sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.BLUE + " has been removed from the taglist");
				return true;
			}
			else
			{
				sender.sendMessage(ChatColor.RED + " That tag name does not exist!");
				return false;
			}
		} catch (IndexOutOfBoundsException ioo)
		{
			sender.sendMessage(ChatColor.RED + "/dtrem <groupname>");
		}
		return false;
	}
	
}
