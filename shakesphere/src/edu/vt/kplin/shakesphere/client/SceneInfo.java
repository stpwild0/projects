package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SceneInfo implements Serializable {
	String title;
	int numberOfEvents;
	
	@SuppressWarnings("unused")
	private SceneInfo(){}
	
	public SceneInfo(String title, int numberOfEvents)
	{
		this.title = title;
		this.numberOfEvents = numberOfEvents;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getNumberOfEvents()
	{
		return numberOfEvents;
	}
}
