package edu.vt.kplin.shakesphere.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import edu.vt.kplin.shakesphere.client.Scene;
import edu.vt.kplin.shakesphere.client.SceneEvent;
import edu.vt.kplin.shakesphere.client.Speech;
import edu.vt.kplin.shakesphere.client.SpeechEvent;
import edu.vt.kplin.shakesphere.client.SpeechLine;

public class PlayParser
{
	static XPathFactory xPathFact = XPathFactory.newInstance();
	
	public PlayParser(){}
	
	public static Play parsePlay(String filename) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory docBuilderFact = DocumentBuilderFactory.newInstance();
		docBuilderFact.setNamespaceAware(true);
		DocumentBuilder docBuilder = docBuilderFact.newDocumentBuilder();
		Document document = docBuilder.parse(new File(filename));
		
		XPathExpression playTitleXPath = xPathFact.newXPath().compile("//PLAY/TITLE/text()");
		String title = (String)playTitleXPath.evaluate(document, XPathConstants.STRING);
		
		XPathExpression playSubtitleXPath = xPathFact.newXPath().compile("//PLAY/PLAYSUBT/text()");
		String subtitle = (String)playSubtitleXPath.evaluate(document, XPathConstants.STRING);
		
		XPathExpression actsXPath = xPathFact.newXPath().compile("//PLAY/ACT");
		NodeList acts = (NodeList) actsXPath.evaluate(document, XPathConstants.NODESET);
		Act[] actsArray = parseActs(acts);
		
		return new Play(title, subtitle, actsArray);
	}
	
	private static Act[] parseActs(NodeList actsNodeList) throws XPathExpressionException
	{	
		ArrayList<Act> actsArrayList = new ArrayList<Act>();
		
		for (int i = 0; i < actsNodeList.getLength(); i++)
		{
			Element actElement = (Element)actsNodeList.item(i);
			
			XPathExpression titleXPath = xPathFact.newXPath().compile("TITLE/text()");
			String title = titleXPath.evaluate(actElement);
			
			XPathExpression scenesXPath = xPathFact.newXPath().compile("SCENE");
			NodeList scenes = (NodeList)scenesXPath.evaluate(actElement, XPathConstants.NODESET);
			Scene[] sceneArray = parseScenes(scenes);
			
			Act act = new Act(title, sceneArray);
			actsArrayList.add(act);
		}
		
		Act[] actArray = new Act[actsArrayList.size()];
		return actsArrayList.toArray(actArray);
	}
	
	private static Scene[] parseScenes(NodeList scenesNodeList) throws XPathExpressionException
	{
		ArrayList<Scene> scenesArrayList = new ArrayList<Scene>();
		
		for (int i = 0; i < scenesNodeList.getLength(); i++)
		{
			Element sceneElement = (Element)scenesNodeList.item(i);
			
			XPathExpression titleXPath = xPathFact.newXPath().compile("TITLE/text()");
			String title = titleXPath.evaluate(sceneElement);
			
			XPathExpression eventsXPath = xPathFact.newXPath().compile("SPEECH | STAGEDIR");
			NodeList events = (NodeList)eventsXPath.evaluate(sceneElement, XPathConstants.NODESET);
			SceneEvent[] eventArray = parseEvents(events);
			
			Scene scene = new Scene(title, eventArray);
			scenesArrayList.add(scene);
		}
		
		Scene[] scenesArray = new Scene[scenesArrayList.size()];
		return scenesArrayList.toArray(scenesArray);
	}
	
	private static SceneEvent[] parseEvents(NodeList eventNodeList) throws XPathExpressionException
	{
		ArrayList<SceneEvent> eventArrayList = new ArrayList<SceneEvent>();
		
		for (int i = 0; i < eventNodeList.getLength(); i++)
		{
			Element eventElement = (Element)eventNodeList.item(i);
			
			if(eventElement.getTagName().equals("SPEECH"))
			{
				Speech speech = parseSpeech(eventElement);
				eventArrayList.add(speech);
			}
			else
			{
				//TODO implement for stage dir
			}
		}
		
		SceneEvent[] eventArray = new SceneEvent[eventArrayList.size()];
		return eventArrayList.toArray(eventArray);
	}
	
	private static Speech parseSpeech(Element speechElement) throws XPathExpressionException
	{
		XPathExpression speakerXPath = xPathFact.newXPath().compile("SPEAKER/text()");
		String speaker = speakerXPath.evaluate(speechElement);
		
		XPathExpression speechEventXPath = xPathFact.newXPath().compile("LINE | STAGEDIR");
		NodeList speechEventNodeList = (NodeList)speechEventXPath.evaluate(speechElement, XPathConstants.NODESET);
		SpeechEvent[] speechEventArray = parseSpeechEvents(speechEventNodeList);
		
		return new Speech(speaker, speechEventArray);
	}
	
	private static SpeechEvent[] parseSpeechEvents(NodeList speechEventNodeList)
	{
		ArrayList<SpeechEvent> eventArrayList = new ArrayList<SpeechEvent>();
		
		for (int i = 0; i < speechEventNodeList.getLength(); i++)
		{
			Element speechEventElement = (Element)speechEventNodeList.item(i);
			
			if(speechEventElement.getTagName().equals("LINE"))
			{
				eventArrayList.add(new SpeechLine(speechEventElement.getTextContent()));
			}
			else
			{
				//TODO implement for stage dir
			}
		}
		
		SpeechEvent[] speechEventArray = new SpeechEvent[eventArrayList.size()];
		return eventArrayList.toArray(speechEventArray);
	}
	
}






























