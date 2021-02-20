package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity =0;
    int whippedcream=0;
    int chocoint;
    public void submitOrder(View view) {
        EditText Name = (EditText) findViewById(R.id.editText);
        String name1= Name.getText().toString();

        CheckBox  whippedcreamcheckbox = (CheckBox) findViewById(R.id.checkBox);
        boolean haswhippedcream= whippedcreamcheckbox.isChecked();

        if (haswhippedcream== true)
         whippedcream=2;
        else
            whippedcream=0;
        CheckBox  Chocolatecheckbox = (CheckBox) findViewById(R.id.checkBox2);
        boolean Chocolate = Chocolatecheckbox.isChecked();

        if (Chocolate == true)
        chocoint=1;
        else
            chocoint=0;
        int extratoppingCharges=(whippedcream+chocoint)*quantity;
        int priceOfCoffe=quantity*5;
        int totalPrice=extratoppingCharges+priceOfCoffe;

        String pricemessage= "Name= " + name1 + " \nPrice: "+ totalPrice + " $ \nThank you!" ;

        displayMessage(pricemessage);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:emitra.aligarh@gmail.com")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffe order for "+ name1);
        intent.putExtra(Intent.EXTRA_TEXT,pricemessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }





    public void increment (View view ){
        quantity=quantity+1;
        if (quantity>100) {
            displayMessage("Sorry we can not provide");
            quantity = 100;
        }        else
        display(quantity);
    }
    public void decrement (View view ){
        quantity=quantity-1;
        if (quantity<1){
            displayMessage("Sorry we can not provide");
        quantity=1;}
        else
            display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }



}