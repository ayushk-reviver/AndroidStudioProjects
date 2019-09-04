package com.example.whatstheweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    String datahistory=null,url=null;
    TextView textView;

    public void seeWeather(View view)
    {
        EditText editText= (EditText) findViewById(R.id.editText);
        DownloadTask task = new DownloadTask();

        if(editText != null && !editText.getText().toString().equals(datahistory) && editText.getText().toString().trim().length() > 0)
        {
            datahistory= editText.getText().toString();

            url= "http://api.openweathermap.org/data/2.5/weather?q="+datahistory+"&APPID=33735ffa8c8d8f266bb6eb143ee3e345";

            Log.i("URL", url);

            task.execute(url);
        }
        else
        {
            Log.i("User input invalid", "please enter another city, or valid city");
        }

    }


    public class DownloadTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... urls)
        {
            String result="";
            URL url;
            HttpURLConnection urlConnection=null;

            try {
                url=new URL(urls[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader= new InputStreamReader(in);
                int data = reader.read();

                while(data !=-1)
                {
                    char ch = (char) data;

                    result += ch;

                    data = reader.read();

                    // Log.i("json CHAR", result);
                }
                return result;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.i("JSON", "Not Completed");
                return null;
            }


        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);


            //Log.i("JSON",s);

            try {
                JSONObject jsonObject= new JSONObject(s);
                String userdata= jsonObject.getString("weather");
                //Log.i("JsoN DATA",userdata);

                JSONArray arr= new JSONArray(userdata);

                for(int i=0; i< arr.length(); i++)
                {
                    JSONObject jsonpart= arr.getJSONObject(i);

                    textView.setText(jsonpart.getString("main") + "\n"+jsonpart.getString("description"));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.resultTextView);
    }
}
