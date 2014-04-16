package com.thedreamsanctuary.dreamindex.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;



import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;
import com.thedreamsanctuary.dreamindex.SerializeData;
import com.thedreamsanctuary.dreamindex.confighandler.CfgHandler;

public class Duadd implements CommandExecutor 
{
	SerializeData ser = new SerializeData();
	CfgHandler cfg = new CfgHandler();
	public boolean onCommand(CommandSender sender, Command comm, String str, String[] args) 
	{
		Preconditions.checkArgument(sender instanceof Player, "Must be a player to execute this command");
		try
		{
			System.out.println(args[0] + " " + args[0]);
			if (!ser.data().getPlayerMap().containsEntry(sender.getName(), args[0]))
			{
				int length = ser.data().getEntryCount(sender.getName());
				if (ser.data().getTags().containsKey(args[0]))
				{
					Boolean hasHitLimit = cfg.getTagWearLimit(length);
					if (hasHitLimit.equals(false))
					{
					/*
					 * Adds the player to the map and then adds a tick to the tag list on 
					 * the count of how many people are actually in that group.
					 */
					ser.data().getPlayerMap().put(sender.getName(), args[0]);
					ser.data().iterateUp(args[0]);
					
					
					
					sender.sendMessage(ChatColor.BLUE + "You have added the " + ChatColor.GOLD + args[0] +ChatColor.BLUE +" to your name!");
					return true;
					}
					else
						sender.sendMessage(ChatColor.RED + "You cannot add any more tags as you are already at your limit!");
				}
				else
					sender.sendMessage(ChatColor.RED + "there is no such tag, use /dtcreate to make a new tag");
					
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "You are already wearing that tag");
			}
		} catch (Exception e)
		{
			sender.sendMessage(ChatColor.RED  + "/duAdd <tagname>");
			e.printStackTrace();
		}
		return false;
	}
	
}
