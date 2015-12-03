package com.example.jackherrer.hang_on;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    public void play_click(View view) {
        Intent intent = new Intent(this, gameplay_activity.class);
        startActivity(intent);
    }

    public void settings_click(View view) {
        Intent intent = new Intent(this,settings.class);
        startActivity(intent);
    }
}
