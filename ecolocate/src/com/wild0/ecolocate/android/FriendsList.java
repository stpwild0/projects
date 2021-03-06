package com.wild0.ecolocate.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
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
    
    public void updateLocation(android.location.Location androidLoc)
    {
    	LocationE location = new LocationE(androidLoc);
    	Date time = new Date(location.getTime());
    	
    	updateText(location.toString());
    	
    	if (location.getAccuracy() < 100)
    	{
    		locationManager.removeUpdates(filteredLocation);
    		String xml = location.getXml();
    		updateText("calling server now");
    		new GetServerResponse().execute(xml);
    	}
    }
    
    public void updateText(String text)
    {
    	TextView locationView = (TextView) findViewById(R.id.your_location);
    	locationView.setText(text);
    }
    
    private class GetServerResponse extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... xmls) {
        	String payload = xmls[0];
        	
        	String appEngineUrl = "http://ecolocate-wild0.appspot.com/location";
        	int responseCode = 0;
        	
			try {
				HttpPost request = new HttpPost(appEngineUrl);
				List<NameValuePair> form = new ArrayList<NameValuePair>();
				form.add(new BasicNameValuePair("content", payload));
				
				UrlEncodedFormEntity ent = new UrlEncodedFormEntity(form,HTTP.UTF_8);
	            request.setEntity(ent);
				
				DefaultHttpClient client = new DefaultHttpClient();
				
				HttpResponse response = client.execute(request);
				
				
				responseCode = response.getStatusLine().getStatusCode();
	        	
	        	//if (responseCode == HttpURLConnection.HTTP_OK)
	        	//{
	        		String line;
					  BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					  StringBuffer buffer = new StringBuffer();
					  while ((line = in.readLine()) != null) {
					    buffer.append(line);
					  }
					  
					  LocationE loc = new LocationE("remote", buffer.toString());
					  
					  return loc.toString();
	        	//	}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
            return Integer.toString(responseCode);
        }
        
        protected void onPostExecute(String displayText) {
        	updateText(displayText);
        }
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