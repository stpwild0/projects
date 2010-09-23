package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PlayInfo implements Serializable {
	String title;
	String subtitle;
	ActInfo[] actInfoArray;
	
	@SuppressWarnings("unused")
	private PlayInfo(){}
	
	public PlayInfo(String title, String subtitle, ActInfo[] actInfoArray)
	{
		this.title = title;
		this.subtitle = subtitle;
		this.actInfoArray = actInfoArray;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getSubtitle()
	{
		return subtitle;
	}
	
	public ActInfo[] getActInfoArray()
	{
		return actInfoArray;
	}
}
