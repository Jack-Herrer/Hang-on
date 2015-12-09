package com.example.jackherrer.hang_on;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class settings extends AppCompatActivity {

    action_menu_handler action_menu_handler_class = new action_menu_handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        return action_menu_handler_class.handle_menu(item, this);
    }

    public void onResume(){
        super.onResume();

        // initiate shared prefs

        //SharedPreferences.Editor editor = getSharedPreferences("prefs_settings", MODE_PRIVATE).edit();

        SharedPreferences settings = getSharedPreferences("prefs_settings", 0);

        // apply lives saved/default settings
        SeekBar lives_seekbar = (SeekBar) findViewById(R.id.settings_lives_bar);
        lives_seekbar.setProgress(settings.getInt("lives", 7));
        TextView textViewLives = (TextView) findViewById(R.id.settings_lives_view);
        textViewLives.setText("Lives: " + settings.getInt("lives", 7));

        // apply word length saved/default settings
        SeekBar word_length_bar = (SeekBar) findViewById(R.id.settings_word_length_bar);
        word_length_bar.setProgress(settings.getInt("wordlength", 5));
        TextView textViewWordLength = (TextView) findViewById(R.id.settings_wordlength_view);
        textViewWordLength.setText("Word length: " + settings.getInt("wordlength", 5));

        // apply evil mode saved/default settings
        Switch evil_switch = (Switch) findViewById(R.id.settings_difficulty_switch);
        if  (settings.getBoolean("evil", true) == true){
            evil_switch.setChecked(true);
        }
        else
        {
            evil_switch.setChecked(false);
        }


        // lives seekbar listner
        lives_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            SharedPreferences.Editor editor = getSharedPreferences("prefs_settings", MODE_PRIVATE).edit();

            int progress_changed = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progress_changed = progress;

                TextView textViewWordLength = (TextView) findViewById(R.id.settings_lives_view);
                textViewWordLength.setText("Lives: " + progress_changed);

                editor.putInt("lives", progress_changed);
                editor.commit();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        // word length seekbar listner
        word_length_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            SharedPreferences.Editor editor = getSharedPreferences("prefs_settings", MODE_PRIVATE).edit();

            int progress_changed = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progress_changed = progress;

                TextView textViewWordLength = (TextView) findViewById(R.id.settings_wordlength_view);
                textViewWordLength.setText("Word length: " + progress_changed);

                editor.putInt("wordlength", progress_changed);
                editor.commit();
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}

        });

        // evil mode listner
        evil_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences("prefs_settings", MODE_PRIVATE).edit();
                if (isChecked) {
                    editor.putBoolean("evil", true);
                } else {
                    editor.putBoolean("evil", false);
                }
                editor.commit();

            }
        });


    }

    public void play_game_click(View view) {
        Intent new_game = new Intent(this, gameplay_activity.class);
        this.startActivity(new_game);
        this.finish();
    }
}