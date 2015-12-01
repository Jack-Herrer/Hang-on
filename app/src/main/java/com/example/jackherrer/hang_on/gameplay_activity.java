package com.example.jackherrer.hang_on;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class gameplay_activity extends AppCompatActivity {

    gameplay gameplayclass = new gameplay();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay_activity);
        gameplayclass.initiate_blank_spaces(this);

    }

    public void on_in_game_enter(View view) {
        gameplayclass.on_in_game_enter(this);
    }

}


