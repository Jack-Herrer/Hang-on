package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

public class GoodGameplay extends Gameplay {

    public void handle_input (Activity activity, char ans){

        char letter = ans;
        correct_letter = false;

        // check if letter is in word and update answer
        search_correct_letter(activity, letter);

        if (correct_letter){
            TextView answer_view = (TextView)activity.findViewById(R.id.in_game_answer);
            answer_view.setText(answer);
        }

        //update lives in case of wrong letter
        else{
            lives--;
            TextView lives_view = (TextView)activity.findViewById(R.id.in_game_lives);
            lives_view.setText("Lives: " + lives);

            wrong_guess(letter, activity);

            //check if lives left
            if(lives == 0){
                Toast.makeText(activity,"Game over: You lost", Toast.LENGTH_LONG).show();
            }
        }
    }

}
