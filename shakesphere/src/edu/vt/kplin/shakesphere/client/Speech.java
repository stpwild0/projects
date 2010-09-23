package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Speech extends SceneEvent implements Serializable, Comparable<Speech>{
	
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

	@Override
	public int compareTo(Speech otherSpeech) {
		int thisLength = this.getSpeechEvents().length;
		int thatLength = otherSpeech.getSpeechEvents().length;
		
		int diff = thisLength - thatLength;
		
		if(diff == 0)
		{
			SpeechLine thisFirst = (SpeechLine)this.speechEvents[0];
			SpeechLine thatFirst = (SpeechLine)otherSpeech.speechEvents[0];
			diff = thisFirst.compareTo(thatFirst);
		}
		
		return diff;
	}
}
