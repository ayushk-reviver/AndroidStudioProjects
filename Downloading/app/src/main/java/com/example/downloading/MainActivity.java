package com.example.downloading;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{

    public class DownloadTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... urls)
        {
            Log.i("URL",urls[0]);
            String result=" ";
            URL url;
            HttpURLConnection httpURLConnection=null;

            try {
                url=new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream= httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                int data = inputStreamReader.read();

                while (data !=-1)
                {
                    char current = (char) data;
                    result +=current;

                    inputStreamReader.read();
                }


                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Failed URl";
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed Io";
            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task=new DownloadTask();
        String result=null;
        try {
            result = task.execute("https://zappycode.com/").get();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        Log.i("Result",result);
    }
}
