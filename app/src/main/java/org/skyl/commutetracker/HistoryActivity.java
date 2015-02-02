package org.skyl.commutetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;

import org.skyl.commutetracker.adapters.RowAdapter;
import org.skyl.commutetracker.models.Commute;

import java.util.Collections;
import java.util.List;


public class HistoryActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_history);

        if (findViewById(R.id.fragment_container) != null) {
            // If we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            HistoryListFragment fragment = new HistoryListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment).commit();
        }

    }

    public static class HistoryListFragment extends ListFragment {

        List<Commute> commutes;
        RowAdapter adapter;

        public void onActivityCreated(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // TODO: pagination?
            commutes = Commute.listAll(Commute.class);
            // TODO: get reversed list from Sugar?
            Collections.reverse(commutes);

            adapter = new RowAdapter(this.getActivity(), R.layout.history_row, commutes);
            setListAdapter(adapter);

        }

        public void onListItemClick(ListView listView, View view, int position, long id) {
            Commute commute = commutes.get(position);
            Intent intent = new Intent(this.getActivity(), CommuteDetailActivity.class);
            intent.putExtra("commuteID", commute.getId());
            startActivity(intent);
        }
    }

}

