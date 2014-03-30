package com.thedreamsanctuary.DreamIndex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.google.common.base.Preconditions;


public class SerializeData extends DreamIndex
{	
	public  boolean serializeList(Data d)
	{
		FileOutputStream outputStream;
		
		//if (!new File(pluginLoc + this.getName()).exists())
		File dir = new File(pluginLoc + File.separator);
		
		if (!dir.isDirectory())
		{
			dir.mkdir();
		}
		
		try 
		{
			outputStream = new FileOutputStream(pluginLoc + File.separator + d.getGroupName().toLowerCase() + ".sgf");
			
			ObjectOutputStream out = new ObjectOutputStream(outputStream);
			
			out.writeObject(d);
			out.close();
			
			return true;
		} catch (IOException e) 
		{
			e.printStackTrace();
			//getLogger().log(Level.SEVERE, "Error unable to serialize the group file" + d.getGroupName());
			return false;
		}
			
	}
	public Data deserializeList(String data)
	{
		try
		{
			System.out.println("data"  + pluginLoc + File.separator + data + ".sgf");
			FileInputStream inputStream = new FileInputStream(pluginLoc + File.separator + data + ".sgf");
	        ObjectInputStream in = new ObjectInputStream(inputStream);
	        Data d = (Data) in.readObject();
	        
	        in.close();
	        inputStream.close();
	        
	        return d;
		} catch (IOException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
		return null;
		
	}
	public boolean remGroup(String str)
	{
		File file = new File(pluginLoc + File.separator + str + ".sgf");
		file.delete();
		return false;
		
	}
	public File[] getGroups(Player sender)
	{
		File folder = new File(pluginLoc.toString());
		File files[] = folder.listFiles();
		files = folder.listFiles();
		
		sender.sendMessage("List of groups:");
		for (File f : files)
		{
			sender.sendMessage(Color.BLUE + f.getName());
		}
		return files;	
	}

	public boolean groupExists(String groupName)
	{
		File file = new File(pluginLoc + File.separator + groupName + ".sgf");
		
		if (file.exists())
			return true;
		else
		return false;	
	}
	public boolean memberOf(String groupName, Player player)
	{
		Data d = deserializeList(groupName);

			Preconditions.checkNotNull(d);
			if (d.getGroupList().isEmpty())
				return false;
			
			if (d.getGroupList().contains(player.getName()))
			{
				return true;
			}
			return false;

		
	}
}
