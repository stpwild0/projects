package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Scene implements Serializable {
	String title;
	SceneEvent[] events;
	
	@SuppressWarnings("unused")
	private Scene(){}
	
	public Scene(String title, SceneEvent[] sceneEvents)
	{
		this.title = title;
		this.events = sceneEvents;
	}
	
	public SceneInfo getSceneInfo()
	{
		return new SceneInfo(title, events.length);
	}
	
	public SceneEvent getEvent(int number)
	{
		return events[number];
	}
}
