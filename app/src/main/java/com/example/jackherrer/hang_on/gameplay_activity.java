package com.example.jackherrer.hang_on;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class gameplay_activity extends AppCompatActivity {

    gameplay gameplayclass = new gameplay();
    good_gameplay good_gameplay_class = new good_gameplay();
    action_menu_handler action_menu_handler_class = new action_menu_handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        initialise();
    }

    public void initialise() {
        SharedPreferences settings = getSharedPreferences("prefs_settings", 0);
        gameplayclass.lives = settings.getInt("lives", 7);
        TextView lives_view = (TextView) findViewById(R.id.in_game_lives);
        lives_view.setText("Lives: " + gameplayclass.lives);
        good_gameplay_class.initiate_blank_spaces(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        return action_menu_handler_class.handle_menu(item, this);
    }

    public void on_in_game_enter(View view) {

        //get input
        EditText answer_box = (EditText)findViewById(R.id.in_game_answer_box);
        String answer_letters = String.valueOf(answer_box.getText());

        //handle input after validating
        if(answer_letters.length()==1) {
            char letter = answer_letters.charAt(0);
            if( Character.isLetter(letter)){
              int lives = gameplayclass.lives;
                good_gameplay_class.handle_input(this, lives, letter);}
            else{
               Toast.makeText(this, "invalid input", Toast.LENGTH_SHORT).show();
           }
        }
        else{
            Toast.makeText(this, "invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}


