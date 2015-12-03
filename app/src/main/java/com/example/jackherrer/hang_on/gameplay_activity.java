package com.example.jackherrer.hang_on;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class gameplay_activity extends AppCompatActivity {

    gameplay gameplayclass = new gameplay();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        gameplayclass.initiate_blank_spaces(this);

    }

    public void on_in_game_enter(View view) {
        gameplayclass.on_in_game_enter(this);
    }

}


