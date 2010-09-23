package edu.vt.kplin.shakesphere.server;

import edu.vt.kplin.shakesphere.client.ActInfo;
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
		ActInfo[] actInfoArray = new ActInfo[acts.length];
		for (int i = 0; i < acts.length; i++)
		{
			actInfoArray[i] = acts[i].getActInfo();
		}
		
		return new PlayInfo(title, subtitle, actInfoArray);
	}
	
	public Act getAct(int actNumber)
	{
		return acts[actNumber];
	}
	
	
}
