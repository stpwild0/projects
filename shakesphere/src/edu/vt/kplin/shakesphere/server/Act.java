package edu.vt.kplin.shakesphere.server;

public class Act {
	
	private String title;
	private Scene[] scenes;
	
	private Act(){}
	public Act(String title, Scene[] scenes)
	{
		this.title = title;
		this.scenes = scenes;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getSceneCount()
	{
		return scenes.length;
	}
	
	public Scene getScene(int number)
	{
		return scenes[number];
	}
	
}