package de.upaverlag.app.location;

import android.location.LocationManager;
// TODO Vervollstaendigen

public class GPSmanager {
	
	LocationChecker checkLoc = new LocationChecker();
	LocationManager locManager = checkLoc.locationManager;
	
	GPSmanager (){
		
	}
	
	  public void stopUsingGPS(){
	        if(locManager != null){
	            locManager.removeUpdates(checkLoc);
	        }
	  }
}
	
	
