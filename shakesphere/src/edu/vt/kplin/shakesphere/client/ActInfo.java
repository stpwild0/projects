package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ActInfo implements Serializable {
	String title;
	int numberOfScenes;
	
	@SuppressWarnings("unused")
	private ActInfo(){}
	
	public ActInfo(String title, int numberOfScenes)
	{
		this.title = title;
		this.numberOfScenes = numberOfScenes;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getNumberOfScenes()
	{
		return numberOfScenes;
	}
}
