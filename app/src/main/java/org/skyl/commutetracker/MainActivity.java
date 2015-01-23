package org.skyl.commutetracker;

import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;


public class MainActivity extends ActionBarActivity {

    private boolean started = false;

    private Chronometer chronometer;

    //private long base;

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
        //System.out.println("startCommute!");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    public void stopCommute() {
        //System.out.println("stopCommute!");
        chronometer.stop();
    }
}
