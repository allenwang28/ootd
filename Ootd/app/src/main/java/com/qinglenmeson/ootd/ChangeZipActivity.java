package com.qinglenmeson.ootd;

/**
 * Created by Qing Wang on 5/2/2017.
 */

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangeZipActivity extends AppCompatActivity {
    private EditText zipCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_zip);

        zipCode = (EditText)findViewById(R.id.changezip_editZip);
    }

    public void changeZip(View view) {
        String zip = zipCode.getText().toString();
        int i;
        try {
            i = Integer.parseInt(zip);
            if(i >= 99999 || i < 10000) {
                i = 78705;
            }
        } catch (Exception e) {
            i = 78705;
        }
        MainActivity.setZip(i);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

}
