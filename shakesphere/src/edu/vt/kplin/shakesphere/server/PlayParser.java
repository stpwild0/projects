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

public class PlayParser
{
	static XPathFactory xPathFact = XPathFactory.newInstance();
	
	public PlayParser(){}
	
	public static Play parsePlay(String filename) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory docBuilderFact = DocumentBuilderFactory.newInstance();
		docBuilderFact.setNamespaceAware(true);
		DocumentBuilder docBuilder = docBuilderFact.newDocumentBuilder();
		File file = new File(filename);
		Document document = docBuilder.parse(new File(filename));
		
		XPathExpression actsXPath = xPathFact.newXPath().compile("//PLAY/ACT");
		NodeList acts = (NodeList) actsXPath.evaluate(document, XPathConstants.NODESET);
		
		Act[] actsArray = parseActs(acts);
		
		return new Play(actsArray);
	}
	
	private static Act[] parseActs(NodeList actsNodeList) throws XPathExpressionException
	{	
		ArrayList<Act> actsArrayList = new ArrayList<Act>();
		
		Scene[] emptyScene = new Scene[0];
		
		for (int i = 0; i < actsNodeList.getLength(); i++)
		{
			Element actElement = (Element)actsNodeList.item(i);
			XPathExpression titleXPath = xPathFact.newXPath().compile("/title/text()");
			String title = titleXPath.evaluate(actElement);
			
			Act act = new Act(title, emptyScene);
			actsArrayList.add(act);
		}
		
		
		
		Act[] actArray = new Act[actsArrayList.size()];
		return actsArrayList.toArray(actArray);
	}
}






























