package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import static com.example.jackherrer.hang_on.gameplay_activity.*;

public class gameplay  {

    int lives;

//    public void initiate_lives_wordlength(Context c){
//        SharedPreferences settings = c.getSharedPreferences("prefs_settings", 0);
//        lives = settings.getInt("lives", 7);
//        Toast.makeText(c," " + lives, Toast.LENGTH_LONG).show();

    String answer = "";
    String[] wordlist = {"bergen", "vis", "A", "en", "vest", "kaart"};
    //String[] wordlist = getStringArray(R.words.testArray);
    //String[] wordlist = getResources().getStringArray(R.array.words);

    String word = wordlist[new Random().nextInt(wordlist.length)];

    //HashSet wordlisthash;


    public void initiate_blank_spaces(Activity activity){
        for(int i = 0; i < word.length(); i++){
            answer+=".";
            TextView answer_view = (TextView) activity.findViewById(R.id.in_game_answer);
            answer_view.setText(answer);

        }
    }
    public void on_in_game_enter(Activity activity){

        EditText answer_box = (EditText)activity.findViewById(R.id.in_game_answer_box);
        String answer_letters = String.valueOf(answer_box.getText());

        //check for valid input
        if((answer_letters.length()==1) && (lives != 0) && (!answer.equals(word))) {
            char letter = answer_letters.charAt(0);

            boolean correct_letter = false;

            // check if letter is in word and update answer
            for(int i = 0; i < word.length(); i++){
                if(word.charAt(i) == letter){

                    Toast.makeText(activity, "Bingo", Toast.LENGTH_SHORT).show();

                    char[] myNameChars = answer.toCharArray();
                    myNameChars[i] = letter;
                    answer = String.valueOf(myNameChars);

                    correct_letter = true;

                    // check on win
                    if(answer.equals(word)) {
                        Toast.makeText(activity, "You win!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            if (correct_letter){
                TextView answer_view = (TextView)activity.findViewById(R.id.in_game_answer);
                answer_view.setText(answer);
            }

            //update lives in case of wrong letter
            else{
                lives--;
                TextView lives_view = (TextView)activity.findViewById(R.id.in_game_lives);
                lives_view.setText("Lives: " + lives);

                //check if lives left
                if(lives == 0){
                    Toast.makeText(activity,"Game over: You lost", Toast.LENGTH_LONG).show();

                }
            }

        }

        // toast invalid input
        else if(lives == 0){
            Toast.makeText(activity,"Game over: You lost", Toast.LENGTH_LONG).show();
        }

        else if(answer.equals(word)){
            Toast.makeText(activity,"You win!", Toast.LENGTH_LONG).show();
        }

        else{Toast.makeText(activity,"invalid input", Toast.LENGTH_SHORT).show();}


    }


}

