package com.example.jackherrer.hang_on;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class settings extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


    }

    public void onResume(){
        super.onResume();

        // initiate shared prefs
        SharedPreferences.Editor editor = getSharedPreferences("prefs_settings", MODE_PRIVATE).edit();
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


//    public class SettingsActivity extends AppCompatActivity {
//        public static final String PREFS_NAME = "settingsfile";
//        @Override
//        public void onResume()
//        {
//            super.onResume();
//            // apply settings
//            Switch MySwitch = (Switch) findViewById(R.id.SwitchDiff);
//            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
//            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//            TextView textViewDifficulty = (TextView) findViewById(R.id.textViewDifficulty);
//            textViewDifficulty.setText(settings.getString("difficulty", "Evil"));
//
//            if  (settings.getString("difficulty", "Evil") == "Evil"){
//                MySwitch.setChecked(true);
//            }
//            else
//            {
//                MySwitch.setChecked(false);
//            }
//            SeekBar LivesSeekBar = (SeekBar) findViewById(R.id.LivesSeekBar);
//            LivesSeekBar.setProgress(settings.getInt("lives", 7));
//            TextView textViewLives = (TextView) findViewById(R.id.textViewLives);
//            textViewLives.setText("Lives: " + settings.getInt("lives", 7));
//
//            SeekBar WordLengthSeekBar = (SeekBar) findViewById(R.id.WordLengthSeekBar);
//            WordLengthSeekBar.setProgress(settings.getInt("wordlength",5));
//            TextView textViewWordLength = (TextView) findViewById(R.id.textViewWordLength);
//            textViewWordLength.setText("Word length: " + settings.getInt("wordlength",5));
//
//
//            MySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView,
//                                             boolean isChecked) {
//                    SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
//                    TextView textViewDifficulty = (TextView) findViewById(R.id.textViewDifficulty);
//                    if (isChecked) {
//                        editor.putString("difficulty", "Evil");
//                        textViewDifficulty.setText("Evil");
//                    } else {
//                        editor.putString("difficulty", "Normal");
//                        textViewDifficulty.setText("Normal");
//                    }
//                    editor.commit();
//
//                }
//            });
//
//            // Live Seek Bar
//            LivesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
//
//                int progressChanged = 0;
//
//                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                    progressChanged = progress;
//
//                    TextView textViewLives = (TextView) findViewById(R.id.textViewLives);
//                    textViewLives.setText("Lives: " + progressChanged);
//
//                    editor.putInt("lives", progressChanged);
//                    editor.commit();
//                }
//
//                public void onStartTrackingTouch(SeekBar seekBar) {
//
//                }
//
//                public void onStopTrackingTouch(SeekBar seekBar) {
//
//                }
//            });
//
//            // Word Length Seekbar
//            WordLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
//
//                int progressChanged = 0;
//
//                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
//                    progressChanged = progress;
//
//                    TextView textViewWordLength = (TextView) findViewById(R.id.textViewWordLength);
//                    textViewWordLength.setText("Word length: " + progressChanged);
//
//                    editor.putInt("wordlength", progressChanged);
//                    editor.commit();
//                }
//                public void onStartTrackingTouch(SeekBar seekBar) {
//
//                }
//                public void onStopTrackingTouch(SeekBar seekBar) {
//
//                }
//            });
//        }
//
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.settings_activity);
//
//        }
//
//        public void onButtonClick(View v){
//            startActivity(new Intent(SettingsActivity.this,StartActivity.class));
//
//        }
//
//
//
//
//    }
//
//
}