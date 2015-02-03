package org.skyl.commutetracker.models;

import android.location.Location;

import com.orm.SugarRecord;

import java.util.Date;


public class CommuteLocation extends SugarRecord<CommuteLocation> {

    Commute commute;

    double lat;
    double lon;
    Date date;

    public CommuteLocation() {}

    public CommuteLocation(Commute commute, Location location) {
        this.commute = commute;
        this.lat = location.getLatitude();
        this.lon = location.getLongitude();
        this.date = new Date();
    }

    public String showLatLon() {
        return String.format("%s %s", this.lat, this.lon);
    }
}
