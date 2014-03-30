package com.thedreamsanctuary.DreamIndex;

import org.bukkit.Color;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;
public class OnCommand implements CommandExecutor
{

	public boolean onCommand(CommandSender sender, Command cmd, String argz, String[] args) 
	{
		Data d = new Data();
		//String cmd = cmdInput.toString();
		SerializeData ser= new SerializeData();

			for (int i = 0; i < args.length; i++)
			{
				int j = i + 1;
				/*
					Allows a user to make their own group
					ex: /mkgroup <insert group>
				 */
				if ((args[i].equalsIgnoreCase("mkgroup")) && (sender.hasPermission("dreamIndex.mkgroup")))
				{
					if (ser.groupExists(args[j]) == true)
					{
						sender.sendMessage(Color.RED + "The group already exists!");
					}
					else
					{
						Preconditions.checkNotNull(args[j]);
					d.setGroupName(args[j]);
					ser.serializeList(d);
					}
					
				}
				/*
					remGroup allows a user to remove a group
					ex: /di remgroup <insert group>
				 */
				else if ((args[i].equalsIgnoreCase("remgroup")) && (sender.hasPermission("dreamIndex.remgroup")))
				{
					if (ser.groupExists(args[j]))
					{
					ser.remGroup(args[0]);
					sender.sendMessage(Color.GREEN + "removed group: " + args[j]);
					}
					else
					{
						sender.sendMessage(Color.RED + "That group does not exist");
					}
					
				}
				/*
					Join Command
					/di join <groupname>
				 */
				else if ((args[i].equalsIgnoreCase("join")) && (sender.hasPermission("dreamIndex.join")))
				{
					if (args.length >=2)
					{
						if (ser.groupExists(args[j]))
						{
							
								if (!ser.memberOf(args[j], (Player) sender))
								{	
									
										System.out.println(" MEMBER OF " + args[j]);
										Data d1= new Data();
										d1 = ser.deserializeList(args[j]);
										d1.getGroupList().add(sender.getName());
										
										ser.serializeList(d1);
										
									sender.sendMessage("You have joined: " + args[j]);
		
								}
								else
									sender.sendMessage("You are already a member of this group!");
							
						}
						else
							sender.sendMessage(Color.RED + "The group you are trying to join does not exist!");
					}
					else
					{
						sender.sendMessage("invalid format");
					}
				}
				/*
				 * Leave command
				 * ex: /di leave <groupname>
				 */
				else if ((args[i].equalsIgnoreCase("leave")) && (sender.hasPermission("dreamIndex.leave")))
				{
					if (args.length >=2)
					{
						if (ser.groupExists(args[j]))
						{
							if (ser.memberOf(args[j], (Player) sender))
							{
								Data d1 = ser.deserializeList(args[j]);
								d1.getGroupList().remove(sender.getName());
								ser.serializeList(d1);
							}
							else
							{
								sender.sendMessage(Color.RED + "You are not a member of this group");
							}
						}
						else
						{
							sender.sendMessage(Color.RED + "This group you are trying to leave does not exist");
						}
					}
					else
						sender.sendMessage("I'm not a rocket scientist but I think somethings wrong... invalid format");
					
							
				}
				/*
				 * List of the groups
				 * ex: /di listGroups
				 */
				else if ((args[i].equalsIgnoreCase("listGroups")) && (sender.hasPermission("dreamIndex.listgroup")))
				{
					try
					{
						ser.getGroups((Player) sender);
					} catch (Exception e)
					{
						sender.sendMessage(Color.RED + "You must put a group to list!");
					}
				}
				/*
				 * Lists the players in a group
				 * ex /di list <groupName>
				 */
				else if ((args[i].equalsIgnoreCase("list")) && (sender.hasPermission("dreamIndex.list")))
				{
					Data d1 = ser.deserializeList(args[j]);
					if (d1.getGroupList().isEmpty())
					{
						sender.sendMessage("there is no members in this group");
					}
					else
					{
						sender.sendMessage("Members of: " + d1.getGroupName());
						
						for (String player : d1.getGroupList())
						{
							sender.sendMessage(player);
						}
					}
					

				}
			}
			return false;
	}

}
