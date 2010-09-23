package edu.vt.kplin.shakesphere.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void getPlayInfo(String playName, AsyncCallback<PlayInfo> callback)
		throws IllegalArgumentException;
	}
