package edu.vt.kplin.shakesphere.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	PlayInfo getPlayInfo(String playName) throws IllegalArgumentException;
	Scene getScene(String playName, int actIndex, int sceneIndex);
}
