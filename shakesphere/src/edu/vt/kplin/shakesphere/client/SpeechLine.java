package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpeechLine extends SpeechEvent implements Serializable, Comparable<SpeechLine>
{
	private String text;
	
	@SuppressWarnings("unused")
	private SpeechLine(){}
	
	public SpeechLine(String text)
	{
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public int compareTo(SpeechLine o) {
		return this.text.length() - o.getText().length();
	}
}
