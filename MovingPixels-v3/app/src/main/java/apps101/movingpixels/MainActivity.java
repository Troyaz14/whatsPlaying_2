/*
Copyright (c) 2014 Lawrence Angrave
Dual licensed under Apache2.0 and MIT Open Source License (included below): 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package apps101.movingpixels;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {

	protected static final String TAG = "Penguin!";
	private Bitmap mBitmap;
	private Bitmap mPenguin;
	private int mPHwidth; // Penguin half height
	private int mPHheight; // Penguin half height
	private boolean mTouching;
	private Paint mPaint;
	private float x;
	private float y;
	private float vx = 1;
	private float vy = 1;
	private Canvas mCanvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bitmap original  = BitmapFactory.decodeResource(getResources(),
				R.drawable.rain_penguin_180);
		int desired = getResources().getDimensionPixelSize(R.dimen.penguin);
		mPenguin = Bitmap.createScaledBitmap(original, desired, desired, true);
		// Calculate the half width and height
		mPHwidth = mPenguin.getWidth() / 2;
		mPHheight = mPenguin.getHeight() / 2;

		mBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
		mCanvas = new Canvas(mBitmap);
		mCanvas.drawColor(0xff808080); // Opaque Gray or 'grey'

		mPaint = new Paint();
		// mPaint.setColor(0xff0000ff);
		mPaint.setStrokeWidth(0);
		// paint.setAntiAlias(false);
		// paint.setStyle(Style.STROKE);
		// Surprise!
		// The end point is not drawn by drawLine.
		// Nor by drawRect with default fill style.
		// mCanvas.drawLine(1, 1, 3, 3, mPaint);
		// mCanvas.drawRect(1, 1, 3, 3, mPaint);

		View v = new View(this) {
			@Override
			protected void onDraw(Canvas canvas) {
				// canvas.drawColor(0xffff9900); // Orange
				float scaleX = this.getWidth() / ((float) mBitmap.getWidth());
				float scaleY = this.getHeight() / ((float) mBitmap.getHeight());
				// Log.d("MainActivity","Scale:"+scaleX+","+scaleY);
				canvas.save();
				canvas.scale(scaleX, scaleY);
				// For Android 4.x we also need to pass a paint
				// to turn off filtering  -
				mPaint.setFilterBitmap(false); // Experiment with false vs true
				canvas.drawBitmap(mBitmap, 0, 0, mPaint);
				canvas.restore();

				mPaint.setColor(0x80ffffff); // White
				mPaint.setStyle(Style.FILL_AND_STROKE);

				float angle = SystemClock.uptimeMillis() / 10.0f;
				canvas.translate(x, y);

				if (mTouching)
					canvas.scale(1.2f, 1.2f, mPHwidth, mPHheight);

				canvas.drawCircle(mPHwidth, mPHheight, mPHheight, mPaint);
				canvas.rotate(angle, mPHwidth, mPHheight);
				// canvas.scale(sx, sy, px, py)

				canvas.drawBitmap(mPenguin, 0, 0, null);
				if (y + 2 * mPHheight + vy + 1 >= this.getHeight()) {
					vy = -0.8f * vy;
				} else {
					vy = vy + 1;
				}
				x = x + vx;
				y = y + vy;

				// In 20ms (1/50th second) this view will need to be redrawn
				postInvalidateDelayed(20);
			}
		};
		setContentView(v);
		OnTouchListener onTouch = new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Log.d(TAG, "onTouch!" + event.getAction());
				// This app is not multi-touch aware:
				// When the user performs a multi-touch event the app will get
				// some large action values (because the 'action' parameter
				// encodes additional multi-touch information)
				// So they are ignored by the app
				int action = event.getAction();
				if (action == MotionEvent.ACTION_UP
						|| action == MotionEvent.ACTION_CANCEL) {
					mTouching = false;
				}
				if (action == MotionEvent.ACTION_DOWN) {
					mTouching = true;
				}

				if (action == MotionEvent.ACTION_DOWN
						|| action == MotionEvent.ACTION_MOVE) {
					x = event.getX() - mPHheight;
					y = event.getY() - mPHwidth;
					vx = 0;
					vy = 0;
				}
				float scaleX = mBitmap.getWidth() / ((float) v.getWidth());
				float scaleY = mBitmap.getHeight() / ((float) v.getHeight());
				float pointX = event.getX() * scaleX;
				float pointY = event.getY() * scaleY;
				mCanvas.drawCircle(pointX, pointY, 2, mPaint);
				return true;
			}
		};
		v.setOnTouchListener(onTouch);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
