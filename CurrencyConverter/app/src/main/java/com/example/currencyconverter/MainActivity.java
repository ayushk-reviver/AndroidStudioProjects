package com.example.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void conversion(View view)
    {

        Log.i("Info","Guess Button Pressed");

        EditText txt=(EditText) findViewById(R.id.convertEditText);

        TextView txt2=(TextView) findViewById(R.id.textView2);

        //double d=Double.parseDouble(txt.getText().toString());
        int userinput= Integer.parseInt(txt.getText().toString());
        int randomnum=(int) (Math.random()*(20 - 1)) + 1;

        //String tostemsg= String.format("%.2f",d*68.95);

        if(userinput > randomnum)
        {
            Toast.makeText(this,"Guessed Number is higher than expected",Toast.LENGTH_LONG).show();
        }
        else if(userinput == randomnum)
        {
            Toast.makeText(this,"Congratulations!!! number guessed matched",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Guessed Number is Lower than expected",Toast.LENGTH_LONG).show();
        }

        Log.i("Value","RandomNumber is "+Integer.toString(randomnum));

        //txt2.setText(Integer.toString(randomnum));

        //Toast.makeText(this,"$ to RS is "+tostemsg,Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
