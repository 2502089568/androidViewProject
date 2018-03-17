package com.example.zz.justjava1; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int numberCoffee=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6,-122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null){
//          startActivity(intent);
//        }
        CheckBox whippedSugarCheckBox = (CheckBox) findViewById(R.id.sugar);
        boolean hasWhippedSugar = whippedSugarCheckBox.isChecked();
        CheckBox whippedChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean hasWhippedChocolate = whippedChocolateCheckBox.isChecked();
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        int price = numberCoffee *5;
        String priceMessage = "" ;
        if(numberCoffee>=0 && numberCoffee<=100 ) {
            if (hasWhippedSugar == true) {
                price = price + numberCoffee * 1;
            }
            if (hasWhippedChocolate == true) {
                price = price + numberCoffee * 2;
            }
            priceMessage = priceMessage + "姓名:" + name + "\n" ;
            priceMessage = priceMessage + "总计: $" + price;
            priceMessage = priceMessage + "\n是否加糖:" + hasWhippedSugar;
            priceMessage = priceMessage + "\n是否加巧克力:" + hasWhippedChocolate;
            priceMessage = priceMessage + "\n Thank You!";
            displayMessage(priceMessage);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(intent.EXTRA_SUBJECT,"Just Java order for" +name);
            intent.putExtra(intent.EXTRA_TEXT,"priceMessage ");
        }
    }

    public void increment(View view) {
        if(numberCoffee== 100) {
            Toast.makeText(this,"超出可点单数。",Toast.LENGTH_SHORT).show();
            return ;}
       else{ numberCoffee++;
        display(numberCoffee);}
    }

    public void incrementTen(View view) {
        if(numberCoffee== 100) {
            Toast.makeText(this,"超出可点单数。",Toast.LENGTH_SHORT).show();
            return ;}
        else{ numberCoffee = numberCoffee+10;
            display(numberCoffee);}
    }

    public void decrement(View view) {
        if(numberCoffee== 0 ) {
            Toast.makeText(this,"抱歉，不能为负数",Toast.LENGTH_SHORT).show();
            return ;}
        else{ numberCoffee--;
            display(numberCoffee);}
    }

    public void decrementTen(View view) {
        if(numberCoffee== 0 ) {
            Toast.makeText(this,"抱歉，不能为负数",Toast.LENGTH_SHORT).show();
            return ;}
        else{ numberCoffee = numberCoffee-10;
            display(numberCoffee);}
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}