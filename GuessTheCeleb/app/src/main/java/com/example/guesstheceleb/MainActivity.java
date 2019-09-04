package com.example.guesstheceleb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    String html=null;
    ArrayList<String> celeblist,imagelist;
    ArrayList<Integer> randomceleblist;

    Button btn1,btn2,btn3,btn4;
    ImageView imageView;
    int winner;

    public void userinput(View view)
    {
        Button b= (Button)view;

        String userinput=b.getText().toString();
        String winnername= celeblist.get(randomceleblist.get(winner));

        if(userinput.equals(winnername))
        {
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();
        }



        Log.i("Winner is", winnername);
        //Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();

        generatewinnerlist();
        fillview();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=(Button) findViewById(R.id.button1);
        btn2=(Button) findViewById(R.id.button2);
        btn3=(Button) findViewById(R.id.button3);
        btn4=(Button) findViewById(R.id.button4);
        imageView=(ImageView) findViewById(R.id.imageView);



        DownloadCelebList task = new DownloadCelebList();
        try {
           html = task.execute("http://www.posh24.se/kandisar").get();
           celeblist = new ArrayList<String>();
           imagelist = new ArrayList<String>();
            getceleblist();
            generatewinnerlist();
            fillview();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void fillview()
    {
        btn1.setText(celeblist.get(randomceleblist.get(0)));
        btn2.setText(celeblist.get(randomceleblist.get(1)));
        btn3.setText(celeblist.get(randomceleblist.get(2)));
        btn4.setText(celeblist.get(randomceleblist.get(3)));

        Random random=new Random();

        winner=random.nextInt(4);

        ImageDownloader task = new ImageDownloader();

        try {
            imageView.setImageBitmap(task.execute(imagelist.get(randomceleblist.get(winner))).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void generatewinnerlist()
    {
        Random rand=new Random();
        int randoms=0;
        randomceleblist=new ArrayList<Integer>();

        for(int i=0;i<4;i++)
        {

            if(randomceleblist.size()==0)
            {
                randoms= rand.nextInt(77);
                randomceleblist.add(randoms);
            }else{
                while (randomceleblist.contains(randoms))
                {
                    randoms=rand.nextInt(77);
                }
                randomceleblist.add(randoms);

            }

            Log.i("winnerrandom",String.valueOf(randomceleblist.get(i)));

        }
    }

    public void getceleblist()
    {
        //String[] htmlpars=html.split("<div class=\"listedArticles\">");

        //Log.i("Split 0", "checked" +htmlpars[0]);

        Pattern pattern= Pattern.compile("alt=\"(.*?)\"");
        Matcher matcher= pattern.matcher(html);

        Pattern pattern2= Pattern.compile("<img src=\"(.*?)\" alt=\"");
        Matcher matcher2= pattern2.matcher(html);

        try {

            while (matcher.find()) {
                celeblist.add(matcher.group(1));
                Log.i("List", matcher.group(1));
            }

            while (matcher2.find()) {
                imagelist.add(matcher2.group(1));
                Log.i("List", matcher2.group(1));
            }

            Log.i("Image List Size", String.valueOf(imagelist.size()));
            Log.i("Celeb List Size", String.valueOf(celeblist.size()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();

                Bitmap img = BitmapFactory.decodeStream(in);

                return img;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }
    }

    public class DownloadCelebList extends AsyncTask<String , Void , String>
    {

        @Override
        protected String doInBackground(String... urls)
        {
            String result=null;
            URL url;
            HttpURLConnection connection=null;
            try {
                url= new URL(urls[0]);
                connection=(HttpURLConnection) url.openConnection();
                InputStream io= connection.getInputStream();
                InputStreamReader reader= new InputStreamReader(io);
                int data=reader.read();

                while (data != -1)
                {
                    char ch= (char) data;

                    result += ch;

                    data = reader.read();
                }

                //Log.i("HTML",result);

                return result;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }
}
