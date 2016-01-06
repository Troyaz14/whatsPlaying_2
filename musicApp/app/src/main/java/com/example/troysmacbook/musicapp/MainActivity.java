
package com.example.troysmacbook.musicapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MediaPlayer feedback;
    /*  start the sound file  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Pickle", "onCreate");
        feedback = MediaPlayer.create(this, R.raw.feedback);
        feedback.start();
    }
    /* start the sound file    */
    @Override
    protected void onResume() {
        Log.e("Pickle", "onResume");
        feedback=MediaPlayer.create(this,R.raw.feedback);
        feedback.start();
        super.onResume();
    }
    /* stop the sound file    */
    @Override
    protected void onPause() {
        Log.e("Pickle", "OnPause");
        feedback.stop();
        super.onPause();
    }
protected void onDestroy(){
    Log.e("Pickle", "OnDestroy");
    feedback.stop();
    super.onDestroy();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    /* open the web page for android buttons     */
    public void openWebPage(View v){
        String url = "http://developer.android.com/guide/topics/ui/controls/button.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
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
