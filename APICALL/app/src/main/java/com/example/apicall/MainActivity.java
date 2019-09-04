package com.example.apicall;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity
{

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
                String userdata= jsonObject.getString("data");
                //Log.i("JsoN DATA",userdata);

                JSONArray arr= new JSONArray(userdata);

                for(int i=0; i< arr.length(); i++)
                {
                    JSONObject jsonpart= arr.getJSONObject(i);

                    Log.i("ID",jsonpart.getString("id"));
                    Log.i("User Name",jsonpart.getString("first_name")+" "+ jsonpart.getString("last_name"));
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

        DownloadTask task = new DownloadTask();
        task.execute("https://reqres.in/api/users?page=2");
        //Log.i("JSON", "Completed");


    }
}
