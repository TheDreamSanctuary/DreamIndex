package com.thedreamsanctuary.dreamindex.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;
import com.thedreamsanctuary.dreamindex.SerializeData;
import com.thedreamsanctuary.dreamindex.confighandler.CfgHandler;

public class Dtcreate implements CommandExecutor
{
	SerializeData ser = new SerializeData();
	CfgHandler cfg = new CfgHandler();
	public boolean onCommand(CommandSender sender, Command comm, String str, String[] args) 
	{
		Preconditions.checkArgument(sender instanceof Player, "Must be a player to execute this command");
		try
		{
			if (!ser.data().getTags().containsKey(args[0]))
			{
				Boolean hasHitLimit = cfg.getTagLimit(ser.data().getTags().size());
				
				if (hasHitLimit.equals(false))
				{
					ser.data().getTags().put(args[0], 0);
					sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.BLUE + " has been added to the taglist");
					return true;
				}
				else
					sender.sendMessage("Error the maximum tag limit has been reached!");
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "There is already a group under this name");
			}	
		} catch (Exception e)
		{
			sender.sendMessage(ChatColor.RED + "/dtcreate <tagname>");
		}
		return false;
	}
}

