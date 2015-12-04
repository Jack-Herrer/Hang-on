package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

public class good_gameplay extends gameplay {
    boolean lives_received = false;

    public void handle_input (Activity activity, int lives_l, char ans){

        if(!lives_received){
            lives = lives_l;
            lives_received = true;
        }

        char letter = ans;

        //check for valid input
        if((lives != 0) && (!answer.equals(word))) {

            boolean correct_letter = false;

            // check if letter is in word and update answer
            for(int i = 0; i < word.length(); i++){
                if(word.charAt(i) == letter){

                    Toast.makeText(activity, "Bingo", Toast.LENGTH_SHORT).show();

                    char[] temp_answer = answer.toCharArray();
                    temp_answer[i] = letter;
                    answer = String.valueOf(temp_answer);

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

        else if(lives == 0){
            Toast.makeText(activity,"Game over: You lost", Toast.LENGTH_LONG).show();
        }

        else if(answer.equals(word)){
            Toast.makeText(activity,"You win!", Toast.LENGTH_LONG).show();
        }
    }

}