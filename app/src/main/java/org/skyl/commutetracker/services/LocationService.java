package org.skyl.commutetracker.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import org.skyl.commutetracker.models.Commute;
import org.skyl.commutetracker.models.CommuteLocation;


public class LocationService extends IntentService {

    Commute commute;

    public LocationService() {
        super("LocationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("onHandleIntent!");
        System.out.println(intent);
        Long id = (Long) intent.getExtras().get("commuteID");
        this.commute = Commute.findById(Commute.class, id);
        System.out.println("COMMUTE?");
        System.out.println(id);
        System.out.println(this.commute);

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(
                Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                writeLocation(location);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };
        // Register the listener with the Location Manager to receive location updates
        // TODO get best provider instead of always GPS ...
        // milliseconds and meters - 10 seconds, 10 meters
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 10000, 10, locationListener);

    }

    public void writeLocation(Location location) {
        System.out.println("writeLocation!");
        CommuteLocation commuteLocation = new CommuteLocation(commute, location);
        commuteLocation.save();
    }

}
