package com.example.appnumbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Number
    {
        int number;

        public boolean isSquare()
        {
            Double dbl= Math.sqrt(number);

            if(dbl==Math.floor(dbl))
            {return true;}
            else
            {return false;}
        }

        public boolean isTrangularnumber()
        {
            int x=1;
            int traingularnumbers=1;

            while(traingularnumbers < number)
            {
                x++;
                traingularnumbers = traingularnumbers+x;
            }

            if(traingularnumbers == number)
            { return true;}
            else{return  false;}


        }
    }

    public void testNumber(View view)
    {
        Log.i("info","Button Pressed");

        EditText txt=(EditText) findViewById(R.id.testEditText);


        if(txt.getText().toString().isEmpty())
        {
            Toast.makeText(this,"enter some value idiot", Toast.LENGTH_LONG).show();
        }
        else {

            Number mynumber = new Number();
            mynumber.number = Integer.parseInt(txt.getText().toString());
            String message = txt.getText().toString();

            if (mynumber.isSquare() && mynumber.isTrangularnumber()) {
                Toast.makeText(this, message+ " is both SQUARE and TRIANGULAR", Toast.LENGTH_LONG).show();
            } else if (mynumber.isSquare()) {
                Toast.makeText(this, message+ " is SQUARE", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, message+ " is TRIANGULAR", Toast.LENGTH_LONG).show();
            }
            //System.out.println(mynumber.isSquare());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
