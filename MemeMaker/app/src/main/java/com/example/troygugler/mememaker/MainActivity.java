package com.example.troygugler.mememaker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    public static final int REQUEST_CODE = 1;
    private Bitmap mBitmap;
    private EditText addText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addText = (EditText) findViewById(R.id.editText);

        View.OnClickListener listener = new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // open the Gallery
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select..."), REQUEST_CODE);

            }
        };
        findViewById(R.id.selectPicture).setOnClickListener(listener); //select picture button listener
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK ){
            Uri uri = data.getData();
            Log.d(TAG, uri.toString());
            //Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();
            InputStream stream = null;
            try {
                stream = getContentResolver().openInputStream(uri);       //start stream and load uri

                BitmapFactory.Options options = new BitmapFactory.Options();    //adjust picture size if needed
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

                mBitmap = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),Bitmap.Config.ARGB_8888); //create mutable bitmap
                Canvas c = new Canvas(mBitmap);
                c.drawBitmap(bm, 0, 0, null);           //add bitmap to canvas
                TextPaint tp = new TextPaint();
                tp.setTextSize(bm.getWidth() / 10);       //set text size
                tp.setColor(0xffff0000);                //set font color


                String message1;                    //holds the first 18 chars of the message
                String message2=null;               //holds the rest of the chars
                String message3=null;               //holds the rest of the chars
                String name = addText.getText().toString();  //get message and turn it into a string

                if(name.length()<=18) {                 //if less that 18 chars put use only one variable
                    message1 = name;
                    c.drawText(message1,0, bm.getHeight() / 8, tp);   // draw message
                }else {


                        message1 = name.substring(0, findWord(name));     // if more than 18 use more than one variable



                        message2 = name.substring(findWord(name)+1, name.length());

                        if(message2.length()>18) {
                            message3 = message2.substring(message2.length()/2,message2.length());
                            message2 = message2.substring(0,(message2.length()/2)-1);
                        }

                        c.drawText(message1, 0, bm.getHeight() / 8, tp);   // draw message
                        c.drawText(message2, 0, bm.getHeight() - (bm.getHeight() / 6), tp);   // draw message

                    if(message3!=null)
                        c.drawText(message3, 0, bm.getHeight() - (bm.getHeight() / 25), tp);   // draw message
                }



                bm.recycle();

                ImageView v = (ImageView) findViewById(R.id.imageView);
                v.setImageBitmap(mBitmap);          //add bitmap to image view
            } catch (Exception e) {
                Log.d(TAG,"Decoding Bitmap",e);
            }
        }
    }
    private int findWord (String message){          //**checks to see if a word will be split up or not. if it is,
        int index=18;                               //**if it is then the index is moved until a space is found

        while(index>0){
            if (message.charAt(index) != ' ') {
                   index--;
            }else{
                return index;
            }

        }

        return index;
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

    public void saveAndShare(View v){
        if(mBitmap==null){   //make sure a picture is selected
            return;
        }
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);  //get storage path
        Log.d(TAG, "saveAndShare path = " + path);
        path.mkdirs();
        String filename = "Imagen_"+System.currentTimeMillis()+".jpg";

        File file = new File(path,filename);
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);        //save jpeg to file
            stream.close();
        } catch (Exception e) {
            Log.e(TAG,"saveAndShare (compressing):s",e);
            return;
        }
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        sendBroadcast(intent);      //create file

        Intent share = new Intent(Intent.ACTION_SEND);    //send meme to user
        share.setType("image/jpeg");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share,"Share using..."));

    }

}
