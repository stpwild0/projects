package com.wild0.ecolocate.server;

import java.util.Date;

public class Location
{
	private double mLongitude;
	private double mLatitude;
	private Date mDate;
	
	public Location (double longitude, double latitude, Date date)
	{
		mLongitude = longitude;
		mLatitude = latitude;
		mDate = date;
	}
	
	public String toString()
	{
		return String.format("%2.2f %2.2f %s", mLongitude, mLatitude, mDate);
	}
}
