package com.example.troygugler.bitmapfun;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Bitmap mBitmap;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBitmap = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
// Then write some code to create a Canvas, some Paint and experiment with Canvas here!
// e.g. thick, thin lines and hairlines, edge-only and solid fill styles, paint and color.
// e.g. When will drawing a line or rectangle to (9,9) paint the corner pixel?
// e.g. Can you make semi-transparent paint?
// Create your own View to display your:
        mView = new View(this) {
            protected void onDraw(Canvas canvas) {
                canvas.drawColor(0xffff9900); // Orange
                float scaleX = this.getWidth() / ((float) mBitmap.getWidth());
                float scaleY = this.getHeight() / ((float) mBitmap.getHeight());
                Log.d("MainActivity", "Scale:" + scaleX + "," + scaleY);
                // Use canvas.save(); if you want to restore it later
                canvas.scale(scaleX, scaleY);
                // You can draw your bitmap here ...
                Paint paint = new Paint(); //
                paint.setFilterBitmap(false);
                canvas.drawBitmap(mBitmap, 0, 0, paint);

                //paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(.1f);
                canvas.drawLine(1,1,2,3,paint);

                paint.setColor(0x33ff0000);
                canvas.drawCircle(5,5,2,paint);

            }
        };
        setContentView(mView);



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
}
