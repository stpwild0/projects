package edu.vt.kplin.shakesphere.server;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class PlayParser
{
	public PlayParser(){}
	
	public static Play parsePlay(String filename) throws IOException, ParserConfigurationException, SAXException
	{
		DocumentBuilderFactory docBuilderFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFact.newDocumentBuilder();
		
		Document document = docBuilder.parse(new File(filename));
		NodeList play = document.getElementsByTagName("PLAY");
		NodeList acts = play.item(0).getChildNodes();
		
		return new Play();
	}
	
	private static Act[] getActs(NodeList play)
	{
		ArrayList<Act> acts = new ArrayList<Act>();
		for (int i = 0; i < play.getLength(); i++)
		{
			if (play.item(i).getNodeType() == Node.ELEMENT_NODE)
			{
				Element actElement = (Element)play.item(i);
				Act act = parseAct(actElement);
				acts.add(act);
			}
		}
		
		return acts.toArray()
	}
	
	private static Act parseAct(Element act)
	{
		
	}
}
