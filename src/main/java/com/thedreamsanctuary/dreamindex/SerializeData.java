package com.thedreamsanctuary.dreamindex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.Color;
import org.bukkit.entity.Player;



public class SerializeData
{	
	private static Data d = new Data();
	private String dataFile = "userFiles";
	private File pluginLoc;
	
	public Data data()
	{
		return this.d;
	}
	public void setDataFolder(File pluginLoc)
	{
		this.pluginLoc = pluginLoc;
	}

	public  boolean serializeList()
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
			outputStream = new FileOutputStream(pluginLoc + File.separator + dataFile + ".sgf");
			
			ObjectOutputStream out = new ObjectOutputStream(outputStream);
			
			for (String s : d.getTags())
				System.out.println(s);
			
			
			out.writeObject(this.d);
			out.close();
			
			return true;
		} catch (IOException e) 
		{
			e.printStackTrace();
			//getLogger().log(Level.SEVERE, "Error unable to serialize the group file" + d.getGroupName());
			return false;
		}
			
	}
	public boolean deserializeList()
	{
		try
		{
			FileInputStream inputStream = new FileInputStream(pluginLoc + File.separator + dataFile + ".sgf");
	        ObjectInputStream in = new ObjectInputStream(inputStream);
	        this.d = (Data) in.readObject();
	        
	        in.close();
	        inputStream.close();
	        
	       //this.d = localData;
	       return true;
		} catch (IOException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
			return false;
		}		
		return false;
	}
}
