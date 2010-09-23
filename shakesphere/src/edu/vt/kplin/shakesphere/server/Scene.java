package edu.vt.kplin.shakesphere.server;
import edu.vt.kplin.shakesphere.client.SceneEvent;
import edu.vt.kplin.shakesphere.client.SceneInfo;

public class Scene {
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
