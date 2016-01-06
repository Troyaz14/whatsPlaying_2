package com.example.android.mysunshineapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] forcast = {"Today - Sunny - 88/66", "Tomorrow - Foggy - 70/46", "Weds - Cloudy - 72/63", "Thurs - Rainy - 64/51", "Fri - Foggy - 70/46", "Sat - Sunny - 76/68"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_forcast,R.id.list_item_forcast_textview,forcast);

        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listview_forcast);
        listView.setAdapter(adapter);

        return view;
    }
}
