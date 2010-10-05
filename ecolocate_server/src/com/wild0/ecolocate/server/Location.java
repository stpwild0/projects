package com.wild0.ecolocate.server;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Location
{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private double longitude;
	
	@Persistent
	private double latitude;
	
	@Persistent
	private Date date;
	
	public Location (double longitude, double latitude, Date date)
	{
		this.longitude = longitude;
		this.latitude = latitude;
		this.date = date;
	}
	
	public Key getKey()
	{
		return key;
	}
	
	public String toString()
	{
		return String.format("%2.2f %2.2f %s", longitude, latitude, date);
	}
}
