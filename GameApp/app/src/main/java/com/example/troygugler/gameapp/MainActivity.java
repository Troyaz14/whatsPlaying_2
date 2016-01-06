package com.example.troygugler.gameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void wow (View v){
        Intent intent = new Intent();
        intent.setClass(this,WOWActivity.class);
        startActivity(intent);
    }

    public void UINasa(View v){
        Intent intent = new Intent();
        intent.setClass(this, UINasa.class);
        startActivity(intent);
    }
    public void roundball (View v){
        Intent intent = new Intent();
        intent.setClass(this, Roundball.class);
        startActivity(intent);
    }
    public void jabberwocky (View v){
        Intent intent = new Intent();
        intent.setClass(this, Jabberwocky.class);
        startActivity(intent);
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
}
