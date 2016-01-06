package com.example.android.christmascard;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable animation;
    private MediaPlayer Music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAnimation();
    }

    protected void onResume() {
        Music = MediaPlayer.create(this,
                R.raw.christmasmusic);

        Music.start();
        super.onResume();

    }

    @Override
    protected void onPause() {
        Music.stop();
        Music.release();
        super.onPause();
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

    class Starter implements Runnable {
        public void run() {
            animation.start();
        }
    }

    private void startAnimation(){
        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.christmas1), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas2), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas3), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas4), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas5), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas6), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas7), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas8), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas9), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas10), 5000);
        animation.addFrame(getResources().getDrawable(R.drawable.christmas11), 5000);
        animation.setOneShot(true);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        params.alignWithParent = true;

        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        imageView.setLayoutParams(params);
        imageView.setImageDrawable(animation);
        imageView.post(new Starter());
    }



}
