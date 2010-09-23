package edu.vt.kplin.shakesphere.server;

import edu.vt.kplin.shakesphere.client.ActInfo;
import edu.vt.kplin.shakesphere.client.Scene;
import edu.vt.kplin.shakesphere.client.SceneInfo;

public class Act {
	
	private String title;
	private Scene[] scenes;
	
	public Act(String title, Scene[] scenes)
	{
		this.title = title;
		this.scenes = scenes;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public ActInfo getActInfo()
	{
		SceneInfo[] sceneInfoArray = new SceneInfo[scenes.length];
		for (int i = 0; i < scenes.length; i++)
		{
			sceneInfoArray[i] = scenes[i].getSceneInfo();
		}
		
		return new ActInfo(title, sceneInfoArray);
	}
	
	public Scene getScene(int number)
	{
		return scenes[number];
	}
	
}