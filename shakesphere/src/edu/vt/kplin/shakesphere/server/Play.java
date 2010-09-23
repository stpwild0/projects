package edu.vt.kplin.shakesphere.server;

import edu.vt.kplin.shakesphere.client.PlayInfo;

public class Play {
	String title;
	String subtitle;
	Act[] acts;
	
	@SuppressWarnings("unused")
	private Play(){}

	public Play(String title, String subtitle, Act[] acts) {
		this.title = title;
		this.subtitle = subtitle;
		this.acts = acts;
	}
	
	public PlayInfo getPlayInfo()
	{
		return new PlayInfo(title, subtitle, acts.length);
	}
	
	public Act getAct(int actNumber)
	{
		return acts[actNumber];
	}
	
	
}
