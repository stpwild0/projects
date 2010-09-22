package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpeechStageDir extends SpeechEvent implements Serializable{
	public String text;
	@SuppressWarnings("unused")
	private SpeechStageDir(){}
	public SpeechStageDir(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}

}
