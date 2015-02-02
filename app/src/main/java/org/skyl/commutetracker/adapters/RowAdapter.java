package org.skyl.commutetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.skyl.commutetracker.R;
import org.skyl.commutetracker.models.Commute;

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

        // TODO: add first/last locations?
        TextView beg = (TextView) convertView.findViewById(R.id.commuteBeg);
        TextView end = (TextView) convertView.findViewById(R.id.commuteEnd);

        // TODO: format dates
        beg.setText(commutes.get(position).beg.toString());
        end.setText(commutes.get(position).end.toString());

        return convertView;
    }
}
