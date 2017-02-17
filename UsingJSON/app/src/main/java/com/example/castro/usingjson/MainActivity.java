package com.example.castro.usingjson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute("http://api.openweathermap.org/data/2.5/forecast/city?"
                + "id=524901&APPID=2445ec646f89fe3a796a6c0c6a1d98d2");
    }

    // Async<Task> allows is to operate on the background thread
    // which can be perfect for downloading content in the background
    public class DownloadTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... urls)
        {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try
            {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while(data != -1)
                {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                } // while

            } // try
            catch (MalformedURLException e) { e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }

            return result;
        }

        // this method is called when the background thread
        // completes its task and it can pass information back
        // in this case our result
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try
            {
                JSONObject jsonObject = new JSONObject(result);

                // extract certain parts from the JSON object
                String weatherInfo = jsonObject.getString("weather");

                Log.i("Weather content", weatherInfo);

                // need a way to loop through the JSON and find individual
                // json arrays

                JSONArray jArray = new JSONArray(weatherInfo);

                for(int i = 0; i < jArray.length(); i++)
                {
                    JSONObject jsonElement = jArray.getJSONObject(i);

                    Log.i("main", jsonElement.getString("main"));
                    Log.i("description", jsonElement.getString("description"));
                }

            }
            catch (JSONException e) { e.printStackTrace(); }

            Log.i("Website Content", result);
        } // onPostExecute()
    } // DownloadTask{}
} // {}

