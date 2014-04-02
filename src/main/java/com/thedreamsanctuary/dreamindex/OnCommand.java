package com.thedreamsanctuary.dreamindex;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;

public class OnCommand implements CommandExecutor
{
	SerializeData ser = new SerializeData();
	
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) 
	{
		// TODO Auto-generated method stub
		//String dFile = "datafile";		
		
		Preconditions.checkArgument(sender instanceof Player);
		
		if (cmd.getName().equalsIgnoreCase("dtcreate"))
		{
			dtCreate(args, (Player) sender);
		}
		else if (cmd.getName().equalsIgnoreCase("dtrem"))
		{
			dtRem(args, (Player) sender);
		}
		else if (cmd.getName().equalsIgnoreCase("dtlist"))
		{
			dtList(args, (Player) sender);
		}
		else if (cmd.getName().equalsIgnoreCase("duadd"))
		{	
			duAdd(args, (Player) sender);
		}
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		else if (cmd.getName().equalsIgnoreCase("durem"))
		{	
			duRem(args, (Player) sender);
		}
		else if (cmd.getName().equalsIgnoreCase("duinfo"))
		{
			duInfo(args, (Player) sender);
		}
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		else if (cmd.getName().equalsIgnoreCase("dsearch"))
		{
			dSearch(args, (Player) sender);
			
		}
		return false;
	}
	/*========================================
	 * Handler Functions
	 ========================================*/
	
	
	
	/*==================
	 * DTCREATE
	 ==================*/
	private boolean dtCreate(String[] args,Player sender)
	{
		System.out.println("thrown");
		if (!tContains(args[0]))
		{
			ser.data().getTags().add(args[0]);
			sender.sendMessage(args[0] + "has been added to the taglist");
			return true;
		}
		else
		{
			sender.sendMessage("There is already a group under this name");
			return false;
		}			
	}
	/*==================
	 * DTREM
	 ==================*/
	private boolean dtRem(String[] args, Player sender)
	{
		System.out.println("thrown");
		if (tContains(args[0]))
		{
			ser.data().getTags().remove(args[0]);
			sender.sendMessage(args[0] + "has been removed from the taglist");
			return true;
		}
		else
		{
			sender.sendMessage(Color.red + "That tag name does not exist!");
			return false;
		}
	}
	/*==================
	 * lists all the tags
	 ==================*/
	private boolean dtList(String[] args, Player sender)
	{
		sender.sendMessage("List of tags");
		
		for (String tags : ser.data().getTags())
			sender.sendMessage(tags);
		return true;
		
	}
	/*==================
	 * DUADD
	 ==================*/
	private boolean duAdd(String[] args, Player sender)
	{
		System.out.println("thrown");
		if (!ser.data().getPlayerMap().containsEntry(sender.getName(), args[0]))
		{
			if (ser.data().getTags().contains(args[0]))
			{
			ser.data().getPlayerMap().put(sender.getName(), args[0]);
			sender.sendMessage("You have added the " + args[0] + " to your name!");
			return true;
			}
			else
				sender.sendMessage(Color.red + "there is no such tag, use /dtcreate to make a new tag");
		}
		else
		{
			sender.sendMessage(Color.red + "You are already wearing that tag");
		}
		
	return false;	
	}
	/*==================
	 * DUREM
	 ==================*/
	private boolean duRem(String[] args, Player sender)
	{
		if (ser.data().getPlayerMap().containsEntry(sender.getName(), args[0]))
		{
			ser.data().getPlayerMap().remove(sender.getName(),args[0]);
			sender.sendMessage(args[0] + "has been removed from you");
		}
		else
			sender.sendMessage("You are not wearing that tag!");
		return false;
		
	}
	/*==================
	 * Dsearch
	 ==================*/
	private boolean dSearch(String[] args, Player sender)
	{
		if (args.length >= 1)
		{
			
			sender.sendMessage(Color.orange + "Members of: " + Color.blue + args[0]);
			for (Entry<String, String> entry : ser.data().getPlayerMap().entries())
			{
				if (entry.getValue().equals(args[0]))
					sender.sendMessage(Color.lightGray + entry.getKey());
			}
		}			
		return false;
	}
	/*==================
	 * DuInfo
	 ==================*/
	private boolean duInfo(String[] args, Player sender)
	{
		//String description[] = null;
		String str = null;
		String player;
		if (args.length >= 1)
		{
			player =  Bukkit.getPlayer(args[0]).getName();
			if (ser.data().getPlayerMap().containsKey(player))
			{
				
				for (Entry<String, String> entry : ser.data().getPlayerMap().entries())
				{
					if (entry.getValue().equals(player))
						str = str.concat(" " + entry.getValue());
				}
				
				sender.sendMessage(Color.orange + "Playername: " + Color.blue + args[0]);
				sender.sendMessage(Color.orange + "tags: " + Color.blue + str);
				sender.sendMessage(Color.orange + "description: not implemented yet");
				
			}
			else
				sender.sendMessage("That user is not currently listed!");
		}
		else
			sender.sendMessage(Color.red + "must input a user!");
		return false;
	}
	private boolean tContains(String tagName)
	{
		if (ser.data().getTags().contains(tagName.toLowerCase()))
			return true;
		else
			return false;
	}
	

}

