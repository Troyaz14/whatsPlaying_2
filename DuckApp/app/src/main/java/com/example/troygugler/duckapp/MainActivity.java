package com.example.troygugler.duckapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText mName;
    private EditText mPhone;
    private EditText mEmail;
    private EditText comment;
    private View mduck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = (EditText) findViewById(R.id.name);
        mPhone = (EditText) findViewById(R.id.phone);
        mEmail = (EditText) findViewById(R.id.email);
        comment = (EditText) findViewById(R.id.comments);
        mduck = findViewById(R.id.duck);
        TextWatcher watcher = new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String changeComments = s.toString();
                boolean valid = changeComments.length() > 0 && changeComments.toLowerCase().indexOf("duck") == -1;
                View view = findViewById(R.id.duck);

                boolean isVisible = view.getVisibility() == View.VISIBLE;
                if (isVisible == valid) {
                    return;
                }


                Animation anim;
                if (valid) {
                    view.setVisibility(View.VISIBLE);
                    anim = AnimationUtils.makeInAnimation(MainActivity.this, true);

                } else {
                    anim = AnimationUtils.makeOutAnimation(MainActivity.this, true);
                    view.setVisibility(View.INVISIBLE);
                }
                view.startAnimation(anim);
            }
        };

        comment.addTextChangedListener(watcher);
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

    public void processForm(View view) {
        String newComment = comment.getText().toString();
        String eMail = mEmail.getText().toString();
        String phone = mPhone.getText().toString();
        String name = mName.getText().toString();

        // Intent i = new Intent(ACTION_VIEW);
        // i.setType("text/plain");
        //i.putExtra(Intent.EXTRA_TEXT,"What a wonderful app!");

        //  Intent intent = new Intent(Intent.ACTION_VIEW);
        //  intent.setData(Uri.parse("sms: " + phone));
        //  intent.putExtra("sms_body", newComment);
        // startActivity(intent);

        Intent i = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", eMail, null)
        );
        i.putExtra(Intent.EXTRA_SUBJECT, "important news");

        String message = name + " says..  \n" + newComment;
        if (phone.length() > 0) {
            message = message + "\nPhone: " + phone;
        }

        i.putExtra(Intent.EXTRA_TEXT, message);

        if (i.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(getApplicationContext(), "Configure your email client!", Toast.LENGTH_LONG).show();
        } else {
            startActivity(Intent.createChooser(i, "Please choose your email app"));
        }


        try {
            startActivity(i);
        } catch (Exception ex) {
            Toast.makeText(this.getApplicationContext(), "Cannot send Email!", Toast.LENGTH_LONG).show();
        }


        //int position = eMail.indexOf("@");
        /*if(position==-1)
        {
            Toast.makeText(this.getApplicationContext(), "Invalid email address!", Toast.LENGTH_LONG).show();
            mEmail.requestFocus();
            return;
        }

        if(name.equals("Fred")){
            Toast.makeText(this.getApplicationContext(), "Hi Fred", Toast.LENGTH_LONG).show();
            mEmail.requestFocus();
        }
        try {
            int value = Integer.parseInt(phone);
            Log.d("MainActivity", "Phone number: " + value);
        }catch (Exception e){
            Log.d("MainActivity", "Invalid Phone number: " + phone);

        }

        String username= eMail.substring(0,position);
        String thankyou= "Thankyou " +username+ "!";

        Toast.makeText(this.getApplicationContext(), thankyou, Toast.LENGTH_LONG).show();


        Toast.makeText(this.getApplicationContext(), newComment, Toast.LENGTH_LONG).show();
*/
        Animation anim = AnimationUtils.makeOutAnimation(this, true);
        mduck.startAnimation(anim);
/*
        view.setVisibility(View.INVISIBLE);
        Toast.makeText(this.getApplicationContext(),R.string.app_name,Toast.LENGTH_LONG).show();
        */
    }
}
