package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PlayInfo implements Serializable {
	String title;
	String subtitle;
	int numberOfActs;
	
	@SuppressWarnings("unused")
	private PlayInfo(){}
	
	public PlayInfo(String title, String subtitle, int numberOfActs)
	{
		this.title = title;
		this.subtitle = subtitle;
		this.numberOfActs = numberOfActs;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getSubtitle()
	{
		return subtitle;
	}
	
	public int getNumberOfActs()
	{
		return numberOfActs;
	}
}
