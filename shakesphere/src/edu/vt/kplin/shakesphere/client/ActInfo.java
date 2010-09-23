package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ActInfo implements Serializable {
	String title;
	SceneInfo[] sceneInfoArray;
	
	@SuppressWarnings("unused")
	private ActInfo(){}
	
	public ActInfo(String title, SceneInfo[] sceneInfoArray)
	{
		this.title = title;
		this.sceneInfoArray = sceneInfoArray;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public SceneInfo[] getSceneInfoArray()
	{
		return sceneInfoArray;
	}
}
