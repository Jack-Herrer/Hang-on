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
import java.util.Random;

public class GameplayActivity extends AppCompatActivity {
    EvilGameplay evilGameplay_class = new EvilGameplay();
    GoodGameplay goodGameplay_class = new GoodGameplay();
    ActionMenuHandler actionMenuHandler_class = new ActionMenuHandler();

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
        boolean evil = settings.getBoolean("evil", true);

        if(evil){
            evilGameplay_class.lives = settings.getInt("lives", 7);
            TextView lives_view = (TextView) findViewById(R.id.in_game_lives);
            lives_view.setText("Lives: " + evilGameplay_class.lives);
            evilGameplay_class.initiate_blank_spaces(this);
            evilGameplay_class.wordlist = evilGameplay_class.loadwordlist(this);
            evilGameplay_class.word = evilGameplay_class.wordlist[new Random().nextInt(evilGameplay_class.wordlist.length)];
        }

        else{
            goodGameplay_class.lives = settings.getInt("lives", 7);
            TextView lives_view = (TextView) findViewById(R.id.in_game_lives);
            lives_view.setText("Lives: " + goodGameplay_class.lives);
            goodGameplay_class.initiate_blank_spaces(this);
            goodGameplay_class.wordlist = goodGameplay_class.loadwordlist(this);
            goodGameplay_class.word = goodGameplay_class.wordlist[new Random().nextInt(goodGameplay_class.wordlist.length)];
            Toast.makeText(this, "word:" + goodGameplay_class.word, Toast.LENGTH_SHORT).show();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        return actionMenuHandler_class.handle_menu(item, this);
    }

    public void onInGameEnter(View view) {
        SharedPreferences settings = getSharedPreferences("prefs_settings", 0);
        boolean evil = settings.getBoolean("evil", true);

        //get input
        EditText answer_box = (EditText)findViewById(R.id.in_game_answer_box);
        String answer_letters = String.valueOf(answer_box.getText());

        //handle input after validating
        if(answer_letters.length()==1) {
            char letter = Character.toUpperCase(answer_letters.charAt(0));


            if(evil && Character.isLetter(letter) && evilGameplay_class.guessed.indexOf(letter) < 0
                    && evilGameplay_class.answer.indexOf(letter) < 0 ) {
                evilGameplay_class.handle_input(this, letter);
            }
            else if(!evil && Character.isLetter(letter) && goodGameplay_class.guessed.indexOf(letter) < 0
                    && goodGameplay_class.answer.indexOf(letter) < 0 ) {
                goodGameplay_class.handle_input(this, letter);
            }
            else{
               Toast.makeText(this, "invalid input", Toast.LENGTH_SHORT).show();
           }
        }
        else{
            Toast.makeText(this, "invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}


