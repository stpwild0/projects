package edu.vt.kplin.shakesphere.server;

public class Play {

	private Act[] acts;
	
	@SuppressWarnings("unused")
	private Play(){}

	public Play(Act[] acts) {
		this.acts = acts;
	}
	
	public int getActCount()
	{
		return acts.length;
	}
	
	public Act getAct(int actNumber)
	{
		return acts[actNumber];
	}
}
