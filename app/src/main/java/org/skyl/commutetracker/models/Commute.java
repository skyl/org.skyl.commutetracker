package org.skyl.commutetracker.models;


import com.orm.SugarRecord;

import java.util.Date;


public class Commute extends SugarRecord<Commute> {

    // new Date() is good enough?
    // http://stackoverflow.com/q/5369682
    public Date beg;
    public Date end;

    public Commute() {}

    public void start() {
        this.beg = new Date();

        // shouldn't have to ... but,
        //      Caused by: java.lang.NullPointerException:
        // Attempt to invoke virtual method 'long java.util.Date.getTime()'
        // on a null object reference
        //?
        // Should have this null or 0 or special value to signify that
        // this Commute is still in progress.
        this.end = new Date();
        this.save();
    }

    public void stop() {

        this.end = new Date();
        this.save();
    }

}