package com.example.troygugler.gameapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

public class Jabberwocky extends AppCompatActivity {
    WebView myWebView;
    private MediaPlayer scaryMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jabberwocky);

        //view jabberwocky.html
        myWebView = (WebView) findViewById(R.id.webView2);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.loadUrl("file:///android_asset/jabberwocky.html");

    }

    @Override
    protected void onResume() {
        scaryMusic = MediaPlayer.create(this,
                R.raw.creepymusicapp);

        scaryMusic.start();
        super.onResume();

    }

    @Override
    protected void onPause() {
        scaryMusic.stop();
        scaryMusic.release();
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jabberwocky, menu);
        return true;
    }
    public void openWikiPage(View v) {
        String url = "https://en.wikipedia.org/wiki/Jabberwocky";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void openPicture(View v) {
        myWebView = (WebView) findViewById(R.id.webView2);
        //myWebView.getSettings().setLoadWithOverviewMode(true);
        //myWebView.getSettings().setUseWideViewPort(true);
        //myWebView.getSettings().setBuiltInZoomControls(true);

        myWebView.loadUrl("file:///android_asset/scary.jpg");

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
