package com.example.android.newemailappmom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {
    String eMail=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);


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

    public void exitApp(View myView){
        this.finish();
        System.exit(0);
    }
    public void email(View myView){
        eMail="";
        //View view =  getLayoutInflater().inflate(R.layout.content_main, null);

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.contact1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.contact2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.contact3);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.contact4);
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.contact5);
        CheckBox checkBox6 = (CheckBox) findViewById(R.id.contact6);
        CheckBox checkBox7 = (CheckBox) findViewById(R.id.contact7);

        if(checkBox1.isChecked()==true){
            eMail = "guglert80@gmail.com; ";
        }

        System.out.println("checkbox 1 checked? " + checkBox1.isChecked());
        if(checkBox2.isChecked()==true){
            eMail = eMail+"cory.gugler@gmail.com; ";
        }
        if(checkBox3.isChecked()==true){
            eMail = eMail+"mgugler@gmail.com; ";
        }
        if(checkBox4.isChecked()==true){
            eMail = eMail+"pgugler@gmail.com; ";
        }
        if(checkBox5.isChecked()==true){
            eMail = eMail+"adrian.gugler@gmail.com; ";
        }
        if(checkBox6.isChecked()==true){
            eMail = eMail+"brengugler@gmail.com; ";
        }
        if(checkBox7.isChecked()==true){
            eMail = eMail+"herbg40@gmail.com; ";
        }

        Intent i = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", eMail, null)
        );
        i.putExtra(Intent.EXTRA_SUBJECT, "Hi from Mom.");
        startActivity(i);
    }
}
