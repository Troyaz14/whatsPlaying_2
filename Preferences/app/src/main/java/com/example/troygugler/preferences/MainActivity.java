package com.example.troygugler.preferences;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY = "count";
    public static final String MAIN_ACTIVITY = MainActivity.class.getSimpleName();
    private SharedPreferences mPrefs;
    private TextView mTextView;
    public String size="size";
    private Runnable action;
    private Bitmap mBitmap;
    private Canvas mCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBitmap =  Bitmap.createBitmap(480,600,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(0xffff6600);

        

        mPrefs = getPreferences(MODE_PRIVATE);
        int count = mPrefs.getInt("count", 0);
        int newSize = mPrefs.getInt("size", 0);


        count = count +1;
        newSize=newSize+10;

        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(KEY, count);
        editor.putInt(size, newSize);
        editor.commit();

        mTextView = new TextView(this);
        mTextView.setTextSize(newSize);
        mTextView.setText("Count : " + count);
        Log.d(MAIN_ACTIVITY, "Count is " + count);
        setContentView(mTextView);
        //setContentView(R.layout.activity_main);

        mTextView.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {

        Runnable add20 = new Runnable(){

            public void run(){
                int clickCount=20 + mPrefs.getInt("clicked", 0);
                mPrefs.edit().putInt("clicked", clickCount).putBoolean("user", true).commit();


                if(clickCount>3000){
                    mTextView.setTextColor(0xffffff00);
                    mTextView.setText("Clicked" + clickCount);
                }else{
                    mTextView.setTextColor(0xff00ff00);
                    mTextView.setText("Clicked" + clickCount);
                }

            }

        };


        mTextView.postDelayed(add20, 200);



    }

}
