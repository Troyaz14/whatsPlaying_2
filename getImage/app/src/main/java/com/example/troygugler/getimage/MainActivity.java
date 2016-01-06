package com.example.troygugler.getimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {


    public static final int REQUEST_CODE = 1;
    private static final String TAG = "TAG";
    private Bitmap mBitmap;
    private MediaPlayer sexist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // open the Gallery
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select..."), REQUEST_CODE);

            }
        };
        findViewById(R.id.button).setOnClickListener(listener);
        sexist = MediaPlayer.create(this,R.raw.sexist);
        sexist.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK ){
            Uri uri = data.getData();
            Log.d(TAG, uri.toString());
            //Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();
            InputStream stream = null;
            try {
                stream = getContentResolver().openInputStream(uri);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(stream, null, options);
                int w = options.outWidth;
                int h = options.outHeight;
                Log.d(TAG, "Bitmap raw size:" + w + " x " + h);
                stream.close();

                int displayW = getResources().getDisplayMetrics().widthPixels;
                int displayH = getResources().getDisplayMetrics().heightPixels;

                options.inJustDecodeBounds = false;
                int sample = 1;

                while (w > sample * displayW || h > sample * displayH){
                    sample = sample * 2;
            }
                Log.d(TAG, "Sampling at" +sample);

                options.inJustDecodeBounds = false;
                options.inSampleSize = sample;

                stream = getContentResolver().openInputStream(uri);
                Bitmap bm =  BitmapFactory.decodeStream(stream,null,options);
                stream.close();

                if(mBitmap!=null)
                    mBitmap.recycle();

                mBitmap = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(mBitmap);
                c.drawBitmap(bm, 0, 0, null);
                TextPaint tp = new TextPaint();
                tp.setTextSize(bm.getHeight()/2);
                tp.setColor(0x800000ff);
                c.drawText("Gotcha",0,bm.getHeight()/2,tp);

                bm.recycle();

                ImageView v = (ImageView) findViewById(R.id.imageView);
                v.setImageBitmap(mBitmap);
            } catch (Exception e) {
                Log.d(TAG,"Decoding Bitmap",e);
            }



        }
    }

    public void onResume(){


        super.onResume();
    }
    public void onPause(){

        super.onPause();
    }
    public void onStop(){


        super.onStop();
    }
    public void onDestroy(){
        sexist.stop();
        sexist.release();
        super.onDestroy();
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

    public void saveAndShare(View v){
        if(mBitmap==null){
            return;
        }
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.d(TAG, "saveAndShare path = " + path);
        path.mkdirs();
        String filename = "Imagen_"+System.currentTimeMillis()+".jpg";

        File file = new File(path,filename);
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.close();
        } catch (Exception e) {
            Log.e(TAG,"saveAndShare (compressing):s",e);
            return;
        }
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        sendBroadcast(intent);

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share,"Share using..."));

    }


}
