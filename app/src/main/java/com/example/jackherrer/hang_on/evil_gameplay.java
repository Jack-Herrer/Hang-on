package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class evil_gameplay extends gameplay {

    public void handle_input (Activity activity, char ans){

        int occurrences;
        char letter = ans;
        String letterstring = "" + letter;
        ArrayList<String> templist_good = new ArrayList<String>();
        ArrayList<String> templist_false = new ArrayList<String>();



        //check for valid input
        if((lives != 0) && (!answer.equals(word))) {

            boolean correct_letter = false;

            // Split wordlist to letter
            for(int i = 0; i < wordlist.length; i++){
                if((wordlist[i].indexOf(letter)) >= 0){
                    templist_good.add(wordlist[i]);
                }
                else{
                    templist_false.add(wordlist[i]);
                }
            }

            // TWEE KEER DEZELFDE LETTER KAN NOG BIJV EERST BOAT EN DAN BOOM
            //"correct guess"
            if(templist_good.size()>templist_false.size()){
                wordlist = templist_good.toArray(new String[templist_false.size()]);

                // pick random word giving away just 1 letter
                do{
                    word = wordlist[new Random().nextInt(wordlist.length)];
                    occurrences = word.length() - word.replace(letterstring, "").length();
                } while(occurrences != 1);
                correct_letter = true;

                //find correct letter in chosen word
                for(int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter) {

                        char[] temp_answer = answer.toCharArray();
                        temp_answer[i] = letter;
                        answer = String.valueOf(temp_answer);

                        // check on win
                        if(answer.equals(word)) {
                            Toast.makeText(activity, "You win!", Toast.LENGTH_LONG).show();
                        }

                        //update list removing double occurrences of guessed letter
                        for(int j = 0; j < templist_good.size(); j++){
                            if(word.indexOf(letter) != wordlist[j].indexOf(letter)){
                                templist_good.remove(j);
                            }

                            if(wordlist[j].length() - word.replace(letterstring, "").length() != 1){
                                templist_good.remove(j);
                            }
                        }
                        wordlist = templist_good.toArray(new String[templist_good.size()]);
                    }
                }


            }

            else {
                wordlist = templist_false.toArray(new String[templist_false.size()]);
            }


            if (correct_letter){
                Toast.makeText(activity, "Bingo", Toast.LENGTH_SHORT).show();
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

        else if(lives == 0){
            Toast.makeText(activity,"Game over: You lost", Toast.LENGTH_LONG).show();
        }

        else if(answer.equals(word)){
            Toast.makeText(activity,"You win!", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(activity,"chosen word: " + word, Toast.LENGTH_LONG).show();
    }



}
