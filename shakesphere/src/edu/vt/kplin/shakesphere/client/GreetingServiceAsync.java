package edu.vt.kplin.shakesphere.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void getActCount(String playName, AsyncCallback<Integer> callback)
		throws IllegalArgumentException;
	
	void getSceneCount(String playName, int actNumber, AsyncCallback<Integer> callback) throws IllegalArgumentException;
}
