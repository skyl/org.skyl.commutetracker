package org.skyl.commutetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;

import org.skyl.commutetracker.models.Commute;
import org.skyl.commutetracker.services.LocationService;


public class MainActivity extends ActionBarActivity {

    private boolean started = false;

    private Chronometer chronometer;

    private Commute commute;

    LocationService locationService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        System.out.println("onCreate!");
        System.out.println(chronometer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleClick(View v) {
        System.out.println("toggleClick!");
        started = !started;
        System.out.println(started);

        if (started) {
            startCommute();
        } else {
            stopCommute();
        }
    }

    public void startCommute() {

        this.commute = new Commute();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        commute.start();

        Context context = this.getApplicationContext();
        Intent intent = new Intent(context, LocationService.class);

        intent.putExtra("commuteID", commute.getId());
        context.startService(intent);

    }

    public void stopCommute() {
        chronometer.stop();
        this.commute.stop();

        Context context = this.getApplicationContext();
        Intent intent = new Intent(context, LocationService.class);
        context.stopService(intent);

    }
}
