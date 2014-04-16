package com.thedreamsanctuary.dreamindex.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;
import com.thedreamsanctuary.dreamindex.SerializeData;

public class Durem implements CommandExecutor 
{
	SerializeData ser = new SerializeData();
	public boolean onCommand(CommandSender sender, Command comm, String str, String[] args) 
	{
		Preconditions.checkArgument(sender instanceof Player, "Must be a player to execute this command");
		try
		{
			if (ser.data().getPlayerMap().containsEntry(sender.getName(), args[0]))
			{
				ser.data().getPlayerMap().remove(sender.getName(),args[0]);
				ser.data().iterateDown(sender.getName());
				
				sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.GOLD + ChatColor.BLUE + " has been removed from you");
				
				//removes the tag from all users wearing it
			}
			else
				sender.sendMessage("You are not wearing that tag!");
			return false;
		} catch (Exception e)
		{
			sender.sendMessage(ChatColor.RED + "/durem <tagname>");
		}
		return false;
		
	}
}
