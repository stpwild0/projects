package edu.vt.kplin.shakesphere.server;

import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import edu.vt.kplin.shakesphere.client.GreetingService;
import edu.vt.kplin.shakesphere.client.PlayInfo;
import edu.vt.kplin.shakesphere.client.Scene;
import edu.vt.kplin.shakesphere.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	Hashtable<String, Play> documents = new Hashtable<String, Play>();

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
	
	private Play getPlay(String playName)
	{
		String lowercaseName = playName.toLowerCase();
		
		if (documents.containsKey(lowercaseName))
		{
			return documents.get(lowercaseName);
		}
		
		try {
			Play play = PlayParser.parsePlay("WEB-INF/shakesphere/"+ playName +".xml");
			documents.put(lowercaseName, play);
			return play;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		throw new IllegalArgumentException();
	}

	@Override
	public PlayInfo getPlayInfo(String playName) throws IllegalArgumentException {
		Play play = getPlay(playName);
		return play.getPlayInfo();
	}
	
	@Override
	public Scene getScene(String playName, int actIndex, int sceneIndex) throws IllegalArgumentException
	{
		try
		{
			Play play = getPlay(playName);
			Act act = play.getAct(actIndex);
			Scene scene = act.getScene(sceneIndex);
			return scene;
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException();
		}
	}
}
