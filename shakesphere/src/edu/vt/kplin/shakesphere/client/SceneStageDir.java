package edu.vt.kplin.shakesphere.client;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SceneStageDir extends SceneEvent implements Serializable {
	private String text;
	
	@SuppressWarnings("unused")
	private SceneStageDir(){}
	public SceneStageDir(String text)
	{
		this.text = text;
	}
	
	public String getText()
	{
		return text;
	}
}
