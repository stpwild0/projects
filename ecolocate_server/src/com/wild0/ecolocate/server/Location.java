package com.wild0.ecolocate.server;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Location
{
	public static SAXParserFactory mSpf = SAXParserFactory.newInstance();
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private double longitude;
	
	@Persistent
	private double latitude;
	
	@Persistent
	private float accuracy;
	
	@Persistent
	private Date date;
	
	public Location (double longitude, double latitude, float accuracy, Date date)
	{
		this.longitude = longitude;
		this.latitude = latitude;
		this.accuracy = accuracy;
		this.date = date;
	}
	
	public Location(String xml) {
		Handler handler = new Handler();
		
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
		
		this.longitude = handler.longitude;
		this.latitude = handler.latitude;
		this.accuracy = handler.accuracy;
		this.date = handler.date;
	}
	
	public void updateLocation (Location location)
	{
		this.longitude = location.longitude;
		this.latitude = location.latitude;
		this.accuracy = location.accuracy;
		this.date = location.date;
	}
	
	public String toString()
	{
		return String.format("%2.2f %2.2f acc:%2.2f %s", longitude, latitude, accuracy, date);
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Key getKey() {
		return key;
	}
	
	public String getXml()
	{
		String xml = String.format("<location><longitude>%f</longitude><latitude>%f</latitude><accuracy>%f</accuracy><time>%d</time></location>",
			this.longitude, this.latitude, this.accuracy, this.date.getTime());
		
		return xml;
	}
	
	protected class Handler extends DefaultHandler
	{
		String tempVal;
		public double longitude;
		public double latitude;
		public float accuracy;
		public Date date;
		
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
			else if (qName.equalsIgnoreCase("accuracy"))
			{
				accuracy = Float.parseFloat(tempVal);
			}
		}
	}
	
}
