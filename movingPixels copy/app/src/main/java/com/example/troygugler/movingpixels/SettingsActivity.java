package com.example.troygugler.movingpixels;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by TroysMacBook on 10/17/15.
 */
public class SettingsActivity extends PreferenceActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            showPreferencesPreHoneycomb();
        } else {
            showPreferencesFragmentStyle(savedInstanceState);
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void showPreferencesFragmentStyle(Bundle savedInstanceState) {

        if(savedInstanceState==null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Fragment fragment = new MyPreferencesFragment();
            transaction.replace(android.R.id.content, fragment);
            transaction.commit();
        }

    }

    @SuppressWarnings("deprecation")
    private void showPreferencesPreHoneycomb() {
        addPreferencesFromResource(R.xml.penguin_prefs);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class MyPreferencesFragment extends PreferenceFragment {
        @SuppressWarnings("deprecation")
        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Log.d("F", "I'm attached to an activity - I have a context!");
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            this.addPreferencesFromResource(R.xml.penguin_prefs);
            return super.onCreateView(inflater, container, savedInstanceState);

        }
    };





}
