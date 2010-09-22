package edu.vt.kplin.shakesphere.server;

import java.io.File;
import java.io.IOException;

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
}
