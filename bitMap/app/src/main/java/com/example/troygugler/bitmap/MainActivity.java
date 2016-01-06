package com.example.troygugler.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

;

public class MainActivity extends AppCompatActivity {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private ImageView mImageView;
    private Paint mPaint;
    private Bitmap mRain;
    private MediaPlayer sexist;
    private int x=0;
    private int y=0;
    private int z=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBitmap =  Bitmap.createBitmap(480, 600, Bitmap.Config.ARGB_8888);

        mRain = BitmapFactory.decodeResource(getResources(), R.drawable.feminist);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(0xff000000);

        mCanvas.drawBitmap(mRain,100,85,null);

        mPaint = new Paint();
        mPaint.setColor(0xffff0000);

        mPaint.setStrokeWidth(16);


        while(z<100) {

            x= (int) (x+(Math.random()*1000));
            y= (int) (y+(Math.random()*1000));
            mCanvas.drawCircle(x, y, 50, mPaint);

            x=0;
            y=0;

            z++;
        }

        mImageView = new ImageView(this);
        mImageView.setImageBitmap(mBitmap);
        setContentView(mImageView);

     //   Camera2 cam = Camera2.open();
     //   Parameters p = cam.getParameters();
     //   p.setFlashMode(Parameters.FLASH_MODE_TORCH);
     //   cam.setParameters(p);
     //   cam.startPreview();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onResume(){
        sexist = MediaPlayer.create(this,R.raw.sexist);
        sexist.start();

    //    Animation anim;
    //    mImageView.setVisibility(View.VISIBLE);
    //    anim = AnimationUtils.makeInAnimation(MainActivity.this, true);
//        mImageView.startAnimation(anim);



        super.onResume();
    }
    public void onPause(){
        sexist.stop();
        sexist.release();
        super.onPause();
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
