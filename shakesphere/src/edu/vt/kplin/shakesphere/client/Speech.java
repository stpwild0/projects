package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Speech extends SceneEvent implements Serializable {
	
	private String speaker;
	private SpeechEvent[] speechEvents;
	
	@SuppressWarnings("unused")
	private Speech(){}
	
	public Speech(String speaker, SpeechEvent[] speechEvents)
	{
		this.speaker = speaker;
		this.speechEvents = speechEvents;
	}
	
	public String getSpeaker()
	{
		return speaker;
	}
	
	public SpeechEvent[] getSpeechEvents() {
		return speechEvents;
	}

}
