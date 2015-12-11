package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Gameplay Class
 * This class handles all aspects which both evil and normal gameplay have in common
 *
 * @version 1
 * @author Michiel van der List  */

public class Gameplay {

    int lives;
    String answer = "";
    String[] wordlist;
    String word;
    String guessed = "";
    boolean correct_letter = false;

    public void initiateBlankSpaces(Activity activity){

        SharedPreferences settings = activity.getSharedPreferences("prefs_settings", Context.MODE_PRIVATE);
        int wordlength = settings.getInt("wordlength", 5);

        // place dot on blank space
        for(int i = 0; i <wordlength; i++){
            answer+=".";
            TextView answer_view = (TextView) activity.findViewById(R.id.in_game_answer);
            answer_view.setText(answer);
        }
    }

    public String[] loadWordlist(Activity activity){
        SharedPreferences settings = activity.getSharedPreferences("prefs_settings", Context.MODE_PRIVATE);
        int wordlength = settings.getInt("wordlength", 5);
        if (wordlength == 0){wordlength++;}

        String[] allwordlist = activity.getResources().getStringArray(R.array.words);
        List<String> wordlist = new ArrayList<String>();

        for (int i=0; i < allwordlist.length;i++) {
            if (allwordlist[i].length() ==wordlength){
                wordlist.add(allwordlist[i]);
            }
        }

        String[] finalwordlist = new String[ wordlist.size() ];
        wordlist.toArray( finalwordlist );
        return finalwordlist;
    }

    public void wrongGuess(char letter, Activity activity){
        guessed += " ";
        guessed += letter;
        lives--;

        TextView guessed_view = (TextView) activity.findViewById(R.id.gameplay_guessed_view);
        guessed_view.setText("Not: " + guessed);

        TextView lives_view = (TextView)activity.findViewById(R.id.in_game_lives);
        lives_view.setText("Lives: " + lives);

        if(lives == 0){
            Toast.makeText(activity,"Game over: You lost\nWord: " + word, Toast.LENGTH_LONG).show();
            Intent to_history= new Intent(activity, HistoryViewActivity.class);
            activity.startActivity(to_history);
            activity.finish();
        }
    }

    public void onWin(Activity activity){
        Intent to_history = new Intent(activity, HistoryViewActivity.class);

        to_history.putExtra("lives", lives);
        to_history.putExtra("mistakes", guessed.length());
        to_history.putExtra("won", true);
        to_history.putExtra("word", word);

        activity.startActivity(to_history);
        activity.finish();
    }

    public void search_correct_letter(Activity activity, char letter){
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == letter){

                char[] temp_answer = answer.toCharArray();
                temp_answer[i] = letter;
                answer = String.valueOf(temp_answer);
                correct_letter = true;

                // check on win
                if(answer.equals(word)) {
                    Toast.makeText(activity, "You win!", Toast.LENGTH_LONG).show();
                    onWin(activity);
                }
            }
        }
    }
}

