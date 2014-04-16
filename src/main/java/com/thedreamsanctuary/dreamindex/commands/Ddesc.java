package com.thedreamsanctuary.dreamindex.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;
import com.thedreamsanctuary.dreamindex.confighandler.CfgHandler;


public class Ddesc implements CommandExecutor
{
	CfgHandler cfg = new CfgHandler();
	public boolean onCommand(CommandSender sender, Command comm, String str, String[] args) 
	{
		String descript = "";
		Preconditions.checkArgument(sender instanceof Player, "Must be a player to execute this command");
		try
		{
			if (sender instanceof Player)
			{
				int length = str.split("\\s+").length;
				
				if (cfg.getDescriptLimit(length) == true)
				{
					for (int i = 0; i < args.length; i++)
						descript += args[i];
					
					cfg.setPlayerDescript((Player) sender, descript);
				}
				else
					sender.sendMessage(ChatColor.RED + "Your description is longer than the set limit!");
			}
		} catch (Exception e)
		{
			sender.sendMessage("/ddesc <message>");
		}		
		return false;
	}
	
}
