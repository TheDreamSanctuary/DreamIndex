package com.thedreamsanctuary.DreamIndex;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String groupName;
	private ArrayList<String> groupList = new ArrayList<String>();
	public String getGroupName() 
	{
		return groupName;
	}
	public void setGroupName(String groupName) 
	{
		this.groupName = groupName;
	}
	
	public ArrayList<String> getGroupList()
	{
		return this.groupList;
	}

}
