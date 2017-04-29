package com.qinglenmeson.ootd;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangeUserActivity extends AppCompatActivity {
    private EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);

        userName = (EditText)findViewById(R.id.changeuser_editTitle);
    }

    public void changeUser(View view) {
        String user = userName.getText().toString();
        Closet closet = Closet.getInstance();
        closet.setOwner(user);
        if (user.equals("")) {
            user = "Default";
        }
        Snackbar.make(findViewById(R.id.changeuser_button), String.format("Changed users to %s", user),
                Snackbar.LENGTH_SHORT)
                .show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

}
