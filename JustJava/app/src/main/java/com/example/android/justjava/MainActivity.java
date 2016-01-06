package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity =0;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view){
        CheckBox whippedCreamCB = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhippedCream = whippedCreamCB.isChecked();

        CheckBox chocolateCB = (CheckBox) findViewById(R.id.chocolate);
        boolean chocolate = chocolateCB.isChecked();

        EditText customer = (EditText) findViewById(R.id.name);


        String Message = createOrderSummary(calculatePrice(hasWhippedCream,chocolate), hasWhippedCream, chocolate, customer.getText().toString());

        displayMessage(Message);
    }
    private String createOrderSummary(int price, boolean whippedCreamCB, boolean chocolate, String customer){

        String priceMessage = "Name: "+customer+"\n"+"Add whipped cream? "+whippedCreamCB+"\nAdd chocolate? "+chocolate+"\nQuantity: " + quantity+ "\n"+"Total = $" + calculatePrice(whippedCreamCB,chocolate) + "\nThank you!";

        return priceMessage;
    }
    private void calculatePrice(int quantity){
        int price = quantity * 5;

    }
    private int calculatePrice(boolean whipCream, boolean chocolate){
        int price=5;
        int whipCreamCost = 1;
        int chocolateCost = 2;

        if(whipCream==true)
            price = price + whipCreamCost;

        if(chocolate==true)
            price = price + chocolateCost;

        return quantity * price;

    }
    private void calculatePrice(){

    }
    private void calculatePrice(int quantity, int costPerCup){
        int price = quantity * costPerCup;

    }



    private void displayMessage(String message){
        String subject = "Your Order";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    public void increment(View view){
        if(quantity==100) {
            //Show error message
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }
    public void decrement(View view){
        if(quantity==1) {
            //Show error message
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);

    }
    /***
     *
     * This method is called when the order button is clicked.
     */
    private void displayQuantity(int displayQuantity){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + displayQuantity);

    }

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
