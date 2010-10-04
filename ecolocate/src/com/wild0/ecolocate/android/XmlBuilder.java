package com.wild0.ecolocate.android;

import android.location.Location;

public class XmlBuilder
{
	public static String location(Location location)
	{
		String xml = String.format("<location><longitude>%f</longitude><latitude>%f</latitude><time>%s</time></location>",
			location.getLongitude(), location.getLatitude(), location.getTime());
		
		return xml;
	}
}