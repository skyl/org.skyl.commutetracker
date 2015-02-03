package org.skyl.commutetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orm.query.Condition;
import com.orm.query.Select;

import org.skyl.commutetracker.R;
import org.skyl.commutetracker.models.Commute;
import org.skyl.commutetracker.models.CommuteLocation;

import java.text.SimpleDateFormat;
import java.util.List;


public class RowAdapter extends ArrayAdapter<Commute> {

    List<Commute> commutes;

    public RowAdapter(Context context, int textViewResourceId, List<Commute> commutes) {
        super(context, textViewResourceId, commutes);
        this.commutes = commutes;
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

        // 4 text views per row
        TextView beg = (TextView) convertView.findViewById(R.id.commuteBeg);
        TextView locationStart = (TextView) convertView.findViewById(R.id.locationStart);
        TextView end = (TextView) convertView.findViewById(R.id.commuteEnd);
        TextView locationEnd = (TextView) convertView.findViewById(R.id.locationEnd);

        Commute commute = commutes.get(position);
        List<CommuteLocation> locations = Select.from(CommuteLocation.class)
                .where(
                        Condition.prop("commute").eq(commute.getId())
                ).orderBy("date").list();

        // TODO: don't record a commute with 0 locations.
        if (locations.size() > 0) {
            CommuteLocation startLocation = locations.get(0);
            CommuteLocation endLocation = locations.get(locations.size() - 1);
            // TODO: reverse geo coding - show name of location rather than lat/lon


            locationStart.setText(startLocation.showLatLon());
            locationEnd.setText(endLocation.showLatLon());
        }

        SimpleDateFormat begFormat = new SimpleDateFormat("EEE MMM d h:mm a");
        SimpleDateFormat endFormat = new SimpleDateFormat("h:mm a");

        beg.setText(begFormat.format(commute.beg));
        end.setText(endFormat.format(commute.end));

        return convertView;
    }

}
