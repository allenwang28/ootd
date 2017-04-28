package com.qinglenmeson.ootd;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class GenerateOutfitActivity extends AppCompatActivity {
    private OutfitPreview outfitPreview;
    private Button acceptButton;
    private Day day;
    private Outfit outfit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_outfit);

        String weatherInfo = "";

        try {
            weatherInfo = new GetWeatherInfo().execute().get();
            Log.d("GenerateOutfitActivity", weatherInfo);
        } catch (InterruptedException e) {
            Log.e("GenerateOutfitActivity", e.getMessage());
        } catch (ExecutionException e) {
            Log.e("GenerateOutfitActivity", e.getMessage());
        }

        day = new Day(weatherInfo);
        Log.d("GenerateOutfitActivity", day.toString());

        outfit = new Outfit();

        outfitPreview = (OutfitPreview)findViewById(R.id.generate_outfit_preview);
        outfitPreview.setOutfit(outfit);

        acceptButton = (Button)findViewById(R.id.generate_outfit_accept);
    }

    public void shuffle(View view) {
        outfit = Outfit.generate(day);
        outfitPreview.setOutfit(outfit);
        acceptButton.setVisibility(View.VISIBLE);
    }

    public void accept(View view) {
        Closet closet = Closet.getInstance();
        closet.saveOutfit(outfit);
    }
}

class GetWeatherInfo extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://samples.openweathermap.org/data/2.5/weather?zip=78705,us&appid=ab6c9e595fe584dc2765d622f3b62da0")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
