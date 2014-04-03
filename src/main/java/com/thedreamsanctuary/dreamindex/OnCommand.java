package com.thedreamsanctuary.dreamindex;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
		try
		{
			System.out.println("thrown");
			if (!ser.data().getTags().contains(args[0]))
			{
				ser.data().getTags().add(args[0]);
				sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.BLUE + " has been added to the taglist");
				return true;
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
	/*==================
	 * DTREM
	 ==================*/
	private boolean dtRem(String[] args, Player sender)
	{
		try
		{
			System.out.println("thrown");
			if (ser.data().getTags().contains(args[0]))
			{
				ser.data().getTags().remove(args[0]);
				sender.sendMessage(ChatColor.GOLD + args[0] + " has been removed from the taglist");
				return true;
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "That tag name does not exist!");
				return false;
			}
		} catch (IndexOutOfBoundsException ioo)
		{
			sender.sendMessage("You must submit a tag to remove");
		}
		return false;
	}
	/*==================
	 * lists all the tags
	 ==================*/
	private boolean dtList(String[] args, Player sender)
	{
		try
		{
			sender.sendMessage("List of tags");
			
			for (String tags : ser.data().getTags())
				sender.sendMessage(tags);
			return true;
		} catch (Exception e)
		{
			sender.sendMessage(ChatColor.RED + "/dtlist     (Psst not sure how you messed this up");
		}
		return false;
		
	}
	/*==================
	 * DUADD
	 ==================*/
	private boolean duAdd(String[] args, Player sender)
	{
		try
		{
			if (!ser.data().getPlayerMap().containsEntry(sender.getName(), args[0]))
			{
				if (ser.data().getTags().contains(args[0]))
				{
				ser.data().getPlayerMap().put(sender.getName(), args[0]);
				sender.sendMessage(ChatColor.BLUE + "You have added the " + ChatColor.GOLD + args[0] +ChatColor.BLUE +" to your name!");
				return true;
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
		}
		
	return false;	
	}
	/*==================
	 * DUREM
	 ==================*/
	private boolean duRem(String[] args, Player sender)
	{
		try
		{
			if (ser.data().getPlayerMap().containsEntry(sender.getName(), args[0]))
			{
				ser.data().getPlayerMap().remove(sender.getName(),args[0]);
				sender.sendMessage(ChatColor.GOLD + args[0] + ChatColor.WHITE + "has been removed from you");
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
	/*==================
	 * Dsearch
	 ==================*/
	private boolean dSearch(String[] args, Player sender)
	{
		try
		{
			if (args.length >= 1)
			{
				
				sender.sendMessage(ChatColor.GOLD + "Members of: " + ChatColor.GOLD + args[0]);
				for (Entry<String, String> entry : ser.data().getPlayerMap().entries())
				{
					if (entry.getValue().equals(args[0]))
						sender.sendMessage(ChatColor.LIGHT_PURPLE + entry.getKey());
				}
			}
		} catch (Exception e)
		{
			sender.sendMessage(ChatColor.RED + "</dsearch <groupname>");
		}
			return false;
			
	}
	/*==================
	 * Description
	 ==================*/
	/*==================
	 * DuInfo
	 ==================*/
	private boolean duInfo(String[] args, Player sender)
	{
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
					
					for (Entry<String, String> entry : ser.data().getPlayerMap().entries())
					{
						if (entry.getKey().equalsIgnoreCase(player))
							str += " [" + entry.getValue() +"] ";
					}
				
					
					sender.sendMessage(ChatColor.GOLD + "Playername: " + ChatColor.GOLD + args[0]);
					
					if (str.isEmpty())
						sender.sendMessage(ChatColor.GOLD + " ");
					else
					sender.sendMessage(ChatColor.GOLD + "tags: " + ChatColor.GOLD + str);
					sender.sendMessage(ChatColor.GOLD  + "description: not implemented yet");
					
				}
				else
					sender.sendMessage("That user is not currently listed!");
			}
			else
				sender.sendMessage(Color.red + "must input a user!");
	    } catch (Exception e)
	    {
	    	e.printStackTrace();
	    	sender.sendMessage("/duinfo <playername>");
	    }
		return false;
	}

	

}

