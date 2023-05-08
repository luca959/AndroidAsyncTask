package com.example.androidasynctask;

import static java.lang.Thread.*;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.URL;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void generate_rdm_str(View view){
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // create random string builder
        StringBuilder sb = new StringBuilder();
        // create an object of Random class
        Random random = new Random();
        // specify length of random string
        int length = 20;
        for(int i = 0; i < length; i++) {
            // generate random index number
            int index = random.nextInt(alphabet.length());
            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);
            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        TextView str=findViewById(R.id.random_text);
        str.setText(randomString);
    }
    public void startTask (View view) {
    // Start the AsyncTask.
    // The AsyncTask has a callback that will update the text view.

        new counter().execute(4000);
    }
    private class counter extends AsyncTask<Integer, Integer, Integer> {
        protected Integer doInBackground(Integer... times) {
            int time_to_wait = times[0];
            for (int i=0; i<time_to_wait;i=i+30){
                try {
                    sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                publishProgress((int) ((i / (float) time_to_wait) * 100));
            }

            return time_to_wait;
        }
        protected void onProgressUpdate(Integer... progress) {
            TextView async= findViewById(R.id.async_text);
            async.setText(progress[0].toString()+"%");
        }
        protected void onPostExecute(Integer result) {
            TextView async= findViewById(R.id.async_text);
            async.setText("Process Complete");

        }

    }

    public void LocalTask(View view) throws InterruptedException {
        TextView str=findViewById(R.id.local_text);
        str.setText("Starting...");
        sleep(500);
        str.setText("Waiting 4000 millisec...");
        sleep(4000);
        str.setText("Finish...");

    }

}