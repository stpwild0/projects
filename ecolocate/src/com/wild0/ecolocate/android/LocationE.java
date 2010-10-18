package com.wild0.ecolocate.android;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.location.Location;

public class LocationE extends Location {

	public static SAXParserFactory mSpf = SAXParserFactory.newInstance();
	
	public LocationE(String provider) {
		super(provider);
	}
	
	public LocationE(Location loc)
	{
		super(loc);
	}
	
	public LocationE(String provider, String xml)
	{
		super(provider);
		
		LocationXmlHandler handler = new LocationXmlHandler();
		
		try {
			SAXParser sp = mSpf.newSAXParser();
			sp.parse(new InputSource(new StringReader(xml)), handler);
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
		
		setLongitude(handler.longitude);
		setLatitude(handler.latitude);
		setAccuracy(handler.accuracy);
		setTime(handler.date);
	}
	
	public String getXml()
	{
		String xml = String.format("<location><longitude>%f</longitude><latitude>%f</latitude><accuracy>%f</accuracy><time>%d</time></location>",
			this.getLongitude(), this.getLatitude(), this.getAccuracy(), this.getTime());
		
		return xml;
	}
	
	protected class LocationXmlHandler extends DefaultHandler
	{
		String tempVal;
		public double longitude;
		public double latitude;
		public float accuracy;
		public long date;
		
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
				date = Long.parseLong(tempVal);
			}
			else if (qName.equalsIgnoreCase("accuracy"))
			{
				accuracy = Float.parseFloat(tempVal);
			}
		}
	}
}
