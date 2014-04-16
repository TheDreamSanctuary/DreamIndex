package com.thedreamsanctuary.dreamindex.commands;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;
import com.thedreamsanctuary.dreamindex.SerializeData;

public class Duinfo implements CommandExecutor 
{
	SerializeData ser = new SerializeData();
	public boolean onCommand(CommandSender sender, Command comm, String strz, String[] args) 
	{
		Preconditions.checkArgument(sender instanceof Player, "Must be a player to execute this command");
		try
	    {
			//String description[] = null;
			String str = " ";
			String player =" ";
			if (args.length >= 1)
			{
				player =  Bukkit.getPlayer(args[0]).getName();
				
				if (player == null)
					sender.sendMessage("Player does not exit");
				
				
				if (ser.data().getPlayerMap().containsKey(player))
				{
					//gets the Tags
					for (Entry<String, String> entry : ser.data().getPlayerMap().entries())
					{
						if (entry.getKey().equalsIgnoreCase(player))
							str += " [" + entry.getValue() +"] ";
					}
					
					//gets the Description
					//String descript = getDescript(sender);
				
					
					sender.sendMessage(ChatColor.GOLD + "Playername: " + ChatColor.BLUE + args[0]);
					
					sender.sendMessage(ChatColor.GOLD + "tags: " + ChatColor.BLUE + str);
					sender.sendMessage(ChatColor.GOLD  + "Description: " + ChatColor.BLUE + "Disabled till further knotice");
					
				}
				else
					sender.sendMessage("That user is not currently listed!");
			}
			else
				sender.sendMessage(ChatColor.RED + "/duinfo <username>");
	    } catch (Exception e)
	    {
	    	e.printStackTrace();
	    	sender.sendMessage("/duinfo <playername>");
	    }
		return false;
	}

}
