package edu.vt.kplin.shakesphere.server;
import edu.vt.kplin.shakesphere.client.SceneEvent;

public class Scene {
	
	public SceneEvent[] events;
	
	@SuppressWarnings("unused")
	private Scene(){}
	public Scene(SceneEvent[] sceneEvents)
	{
		this.events = sceneEvents;
	}
	
	public int getEventCount()
	{
		return events.length;
	}
	
	public SceneEvent getEvent(int number)
	{
		return events[number];
	}
}
