package com.example.troygugler.movingpixels;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private MediaPlayer noise;
    private MediaPlayer bounce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate!");


        setContentView(R.layout.activity_main);




    }

    public void onResume(){
        noise = MediaPlayer.create(this, R.raw.laugh);
        bounce = MediaPlayer.create(this, R.raw.bounce);
        super.onResume();
    }
    public void onPause(){
        noise.stop();
        bounce.stop();
        noise.release();
      
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
/*
    public class PenguinView extends View
    {

        private Bitmap mBitmap;
        private Bitmap mPengiun;
        private int mPHwidth;
        private int mPHhieght;
        private Paint paint;
        private float x;
        private float y;
        private float vx=1;
        private float vy=1;
        private Canvas c;
        private boolean mTouching;


            public PenguinView(Context context) {
            super(context);
                Bitmap original = BitmapFactory.decodeResource(getResources(), R.drawable.rain_penguin);
                int desired = getResources().getDimensionPixelSize(R.dimen.penguin);
                mPengiun = Bitmap.createScaledBitmap(original,desired,desired,true);
                mPHwidth = mPengiun.getWidth()/2;
                mPHhieght = mPengiun.getHeight()/2;


                mBitmap = Bitmap.createBitmap(256,256,Bitmap.Config.ARGB_8888);
                c = new Canvas(mBitmap);
                c.drawColor(0xff808080);  // grey

                paint = new Paint();
                paint.setColor(0Xff0000ff);
                paint.setStrokeWidth(0);
                //c.drawLine(0, 0, (float) 3.5, (float) 3.5, paint);

                //c.drawRect(1,1,3,3,paint);
                View.OnTouchListener onTouch = new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Log.d(TAG,"onTouch!" +event.getAction());
                        int action = event.getAction();
                        if(action==MotionEvent.ACTION_UP||action == MotionEvent.ACTION_CANCEL){
                            mTouching = false;

                        }
                        if (action==MotionEvent.ACTION_DOWN){
                            mTouching = true;
                            noise.start();
                        }

                        if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE) {
                            x = event.getX()-mPHhieght;
                            y = event.getY()-mPHwidth;
                            vx = 0;
                            vy = 0;
                        }
                        float scaleX = mBitmap.getWidth()/((float) v.getWidth());
                        float scaleY = mBitmap.getHeight()/((float) v.getHeight());

                        float pointx = event.getX()*scaleX;
                        float pointy = event.getY()*scaleY;
                        c.drawCircle(pointx,pointy,2,paint);


                        return true;
                    }
                };
                setOnTouchListener(onTouch);
            }

            @Override
            protected void onDraw(Canvas canvas) {
            canvas.drawColor(0xffff9900);

            drawBackground(canvas);

            drawPenguin(canvas);

            doPenguinPhysics();


            //System.out.print("x: "+x + "y: "+y + "vy: "+vy);
            //      Log.d(TAG,"x: "+x + "y: "+y + "vy: "+vy );
            postInvalidateDelayed(20);
        }

        private void drawPenguin(Canvas canvas) {
            paint.setColor(0x80ffffff);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);


            float angle = SystemClock.uptimeMillis()/10.0f;
            canvas.translate(x, y);

            if(mTouching)
                canvas.scale(1.2f,1.2f,mPHwidth,mPHhieght);

            canvas.drawCircle(mPHwidth, mPHhieght, mPHhieght, paint);
            canvas.rotate(angle, mPHwidth, mPHhieght);
            canvas.drawBitmap(mPengiun, 0, 0, null);
        }

        private void drawBackground(Canvas canvas) {
            float scalex =this.getWidth()/((float) mBitmap.getWidth());
            float scaley =this.getHeight()/((float) mBitmap.getHeight());
            canvas.save();
            canvas.scale(scalex, scaley);
            canvas.drawBitmap(mBitmap, 0, 0, paint);
            canvas.restore();
        }

        private void doPenguinPhysics() {
            if (y +2*mPHhieght+vy+1>= this.getHeight()) {
                vy= -0.8f * vy;
                bounce.start();
            }else{
                vy=vy+1;
            }
            x=x+vx;
            y=y+vy;
        }
    };

*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this,SettingsActivity.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
