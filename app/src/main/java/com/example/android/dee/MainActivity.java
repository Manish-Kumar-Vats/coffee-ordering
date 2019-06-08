package com.example.android.dee;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }
    int quantityVarible=1,costOfOneCoffee=5,totalCost;

    public void orderButton(View view) {
        totalCost=quantityVarible*costOfOneCoffee;
        whiperCreamCB();
        chocolateCB();
        changeImg();
      String message="Hello "+nameET()+"\nQuantity: "+quantityVarible+"\nTotal Cost: "+totalCost+getString(R.string.thank_you);
       // displayMessage(message);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "appsupport@YOUR_DOMAIN.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Ordering Coffee for "+nameET());
        intent.putExtra( Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void whiperCreamCB() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.whippeCreamdCB);
        if(checkBox.isChecked()){
            totalCost=totalCost+10;
        }
    }
    private void chocolateCB() {
        CheckBox checkBox = (CheckBox)findViewById(R.id.chocolateCB);;
        if(checkBox.isChecked()){
            totalCost=totalCost+12;
        }
    }
    private void changeImg() {ImageView newSRC=(ImageView)findViewById(R.id.imageView);
        newSRC.setImageResource(R.drawable.newcoffee);
    }
    private String nameET() {   EditText nameET=(EditText)findViewById(R.id.nameEditText);
        String name=nameET.getText().toString();
        return name;
    }
    private void displayMessage(String message) {
        TextView tvMessage=(TextView)findViewById(R.id.summaryTextView);
        tvMessage.setText(message);
    }

    public void incrementQuantity(View view) {
        if (quantityVarible==25){
            Toast.makeText(MainActivity.this,
                    "Maximum Coffees odering quantity is 25", Toast.LENGTH_LONG).show();
        }
        else
        quantityVarible=quantityVarible+1;
        display(quantityVarible);
    }

    public void decrementQuantity(View view) {
        if (quantityVarible==1){
            Toast.makeText(MainActivity.this,
                    "Can't Order 0 coffee", Toast.LENGTH_LONG).show();
        }
        else
        quantityVarible=quantityVarible-1;
        display(quantityVarible);
    }

    private void display(int quantityVarible) {
        TextView tvQuantity=(TextView)findViewById(R.id.quantityTextView);
        tvQuantity.setText(""+quantityVarible);
    }
}
