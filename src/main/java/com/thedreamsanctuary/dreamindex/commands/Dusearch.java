package com.thedreamsanctuary.dreamindex.commands;

import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;
import com.thedreamsanctuary.dreamindex.SerializeData;


public class Dusearch implements CommandExecutor
{
	SerializeData ser = new SerializeData();
	public boolean onCommand(CommandSender sender, Command comm, String str, String[] args) 
	{
		Preconditions.checkArgument(sender instanceof Player, "Must be a player to execute this command");
		try
		{
			if (args.length >= 1)
			{
				if (ser.data().getTags().containsKey(args[0]))
				{
					sender.sendMessage(ChatColor.GOLD + "Members of: " + ChatColor.GOLD + args[0]);
					for (Entry<String, String> entry : ser.data().getPlayerMap().entries())
					{
						if (entry.getValue().equals(args[0]))
							sender.sendMessage(ChatColor.LIGHT_PURPLE + entry.getKey());
					}
				}
				else
					sender.sendMessage(ChatColor.RED + "That group does not exist!");
			}
		} catch (Exception e)
		{
			sender.sendMessage(ChatColor.RED + "</dsearch <groupname>");
		}
			return false;	
	}
}
