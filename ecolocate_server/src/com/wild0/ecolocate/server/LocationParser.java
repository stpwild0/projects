package com.wild0.ecolocate.server;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class LocationParser extends DefaultHandler
{
	public static SAXParserFactory mSpf = SAXParserFactory.newInstance();
	String mXml;
	
	public LocationParser(String xml)
	{
		mXml = xml;
	}
	
	public Location parse() {
		Handler handler = new Handler();
		
		try {
			SAXParser sp = mSpf.newSAXParser();
			sp.parse(new InputSource(new StringReader(mXml)), handler);
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
		
		return handler.getLocation();
	}
	
	public class Handler extends DefaultHandler
	{
		String tempVal;
		double longitude;
		double latitude;
		Date date;
		
		public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws SAXException
		{
			tempVal = "";
		}
		
		public void characters(char[] ch, int start, int length)
			throws SAXException
		{
			tempVal = new String(ch,start,length);
		}
		
		public void endElement(String uri, String localName, String qName)
			throws SAXException
		{
			if (qName.equalsIgnoreCase("longitude"))
			{
				longitude = Double.parseDouble(tempVal);
			}
			else if (qName.equalsIgnoreCase("latitude"))
			{
				latitude = Double.parseDouble(tempVal);
			}
			else if (qName.equalsIgnoreCase("time"))
			{
				date = new Date(Long.parseLong(tempVal));
			}
		}
		
		public Location getLocation()
		{
			return new Location(longitude, latitude, date);
		}
	}
	
}
