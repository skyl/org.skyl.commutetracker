package org.skyl.commutetracker.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import org.skyl.commutetracker.models.Commute;
import org.skyl.commutetracker.models.CommuteLocation;


public class LocationService extends Service {

    public LocationService() {}

    Commute commute;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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
        System.out.println(locationManager);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                System.out.println("onLocationChanged!");
                System.out.println(location);
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

        return START_STICKY;
    }

    public void writeLocation(Location location) {
        System.out.println("writeLocation!");
        CommuteLocation commuteLocation = new CommuteLocation(commute, location);
        commuteLocation.save();
    }

}
