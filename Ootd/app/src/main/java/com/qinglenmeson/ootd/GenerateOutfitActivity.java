package com.qinglenmeson.ootd;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class GenerateOutfitActivity extends AppCompatActivity {
    private OutfitGeneratedView outfitGeneratedView;
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

        day = Day.getInstance(weatherInfo);
        Log.d("GenerateOutfitActivity", day.toString());

        outfit = new Outfit();

        outfitGeneratedView = (OutfitGeneratedView)findViewById(R.id.generate_outfit_preview);
        outfitGeneratedView.setOutfit(outfit);

        acceptButton = (Button)findViewById(R.id.generate_outfit_accept);

    }

    public void shuffle(View view) {
        Outfit o = new Outfit();
        outfit = o.generate();
        outfitGeneratedView.setOutfit(outfit);
        acceptButton.setVisibility(View.VISIBLE);
    }

    public void accept(View view) {
        Closet closet = Closet.getInstance();
        closet.saveOutfit(outfit);

        Snackbar.make(findViewById(R.id.generate_outfit_accept), "Added a new outfit",
                Snackbar.LENGTH_SHORT)
                .show();
        outfit = new Outfit();
        outfitGeneratedView.setOutfit(outfit);
        acceptButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}

class GetWeatherInfo extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?zip=" + MainActivity.getZip() + "&appid=9a996c75660f7f7c743f3a78e5e1769e")
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
