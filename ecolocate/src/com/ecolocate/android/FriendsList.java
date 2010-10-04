package com.ecolocate.android;

import java.util.Date;

import com.ecolocate.android.R;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FriendsList extends Activity{
	LocationManager locationManager;
	FilteredLocation filteredLocation;
	
    /** Called when the activity is first created. */
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        filteredLocation = new FilteredLocation(this);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        
        setContentView(R.layout.main);
        
        final Button pingButton = (Button) findViewById(R.id.button_ping);
        pingButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ping();
            }
        });
        
    }
    
    public void ping()
    {
    	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, filteredLocation);
    	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, filteredLocation);
    	
    	return;
    }
    
    public void updateLocation(Location location)
    {
    	Date time = new Date(location.getTime());
    	//String locString = "cool";
    	String locString = String.format("lat:%2.2f acc:%1.0f t:%s", location.getLatitude(), location.getAccuracy(), time.toString());
    	
    	TextView locationView = (TextView) findViewById(R.id.your_location);
    	locationView.setText(locString);
    }
    
    
    private class FilteredLocation implements LocationListener
    {
    	FriendsList mFriendsList;
    	float lastAcc;
    	
    	public FilteredLocation(FriendsList friendsList) {
    		mFriendsList = friendsList;
    		lastAcc = 200;
    	}

		public void onLocationChanged(Location location) {
			if (location.getAccuracy() < lastAcc)
			{
				lastAcc = location.getAccuracy();
				mFriendsList.updateLocation(location);
			}
			
		}

		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
    }
}