package com.wild0.ecolocate.server;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class User {

	@PrimaryKey
    @Persistent
    private String username;

	@Persistent(dependent = "true")
    private Location location;
    
    public User(String username, Location location)
    {
    	this.username = username.toLowerCase();
    	this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public Location getLocation() {
        return location;
    }
}