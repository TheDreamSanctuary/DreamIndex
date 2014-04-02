package com.thedreamsanctuary.dreamindex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;


public class Data implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String groupName;
	private ArrayList<String> tags = new ArrayList<String>();
	private Multimap<String, String> map = ArrayListMultimap.create();
	private Map<String, String> pDescript = Maps.newHashMap();
	
	public Map<String, String> descriptMap()
	{
		return this.pDescript;
	}
	public Multimap<String,String> getPlayerMap()
	{
		return this.map;
	}
	public ArrayList<String> getTags()
	{
		return this.tags;
	}

}
