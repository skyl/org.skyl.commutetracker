package org.skyl.commutetracker;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.skyl.commutetracker.models.Commute;
import org.skyl.commutetracker.models.CommuteLocation;

import java.util.Collections;
import java.util.List;


// TODO ActionBarActivity too?
public class HistoryActivity extends ListActivity implements AdapterView.OnItemClickListener {

    List<Commute> commutes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // TODO: pagination?
        commutes = Commute.listAll(Commute.class);
        // TODO: get reversed list from Sugar?
        Collections.reverse(commutes);

        RowAdapter adapter = new RowAdapter();
        setListAdapter(adapter);

        this.getListView().setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("OK!" + id + position);
        System.out.println(commutes.get(position).getId());
        Commute commute = commutes.get(position);

        /*
        List<CommuteLocation> locations = CommuteLocation.find(CommuteLocation.class,
                "commute = ?", commute.getId().toString());
        System.out.println(locations);
        */

        // Then you start a new Activity via Intent
        /*
        Intent intent = new Intent();
        intent.setClass(this, ListItemDetail.class);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
        */
        Intent intent = new Intent(this, CommuteDetailActivity.class);
        intent.putExtra("commuteID", commute.getId());
        startActivity(intent);
    }


    private class RowAdapter extends ArrayAdapter<Commute> {

        public RowAdapter() {
            super(HistoryActivity.this, R.layout.history_row, commutes);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null) {
                System.out.println("convertView is null!");
                LayoutInflater inflater = (LayoutInflater) this.getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = (View) inflater.inflate(
                        R.layout.history_row, null);
            }


            // TODO: add first/last locations?
            TextView beg = (TextView) convertView.findViewById(R.id.commuteBeg);
            TextView end = (TextView) convertView.findViewById(R.id.commuteEnd);

            // TODO: format dates
            beg.setText(commutes.get(position).beg.toString());
            end.setText(commutes.get(position).end.toString());
            //end.setText(commutes);

            return convertView;
        }
    }
}

