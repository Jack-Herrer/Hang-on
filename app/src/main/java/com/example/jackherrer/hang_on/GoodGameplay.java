package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.widget.TextView;

/**
 * GoodGameplay Class
 * This class handles part of gameplay specific to normal mode
 *
 * @version 1
 * @author Michiel van der List  */

public class GoodGameplay extends Gameplay {

    public void handleInput(Activity activity, char ans){
        char letter = ans;
        correct_letter = false;

        search_correct_letter(activity, letter);
        if (correct_letter){
            TextView answer_view = (TextView)activity.findViewById(R.id.in_game_answer);
            answer_view.setText(answer);
        } else{
            wrongGuess(letter, activity);
        }
    }
}
