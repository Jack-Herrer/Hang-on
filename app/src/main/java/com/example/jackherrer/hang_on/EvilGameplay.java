package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

/**
 * EvilGameplay Class
 * This class handles part of gameplay specific to evil mode
 *
 * @version 1
 * @author Michiel van der List  */

public class EvilGameplay extends Gameplay {

    public void handle_input (Activity activity, char ans) {
        correct_letter = false;

        char letter = ans;
        String letterstring = "" + letter;

        SharedPreferences settings = activity.getSharedPreferences("prefs_settings", Context.MODE_PRIVATE);
        int wordlength = settings.getInt("wordlength", 5);

        ArrayList<String> templistLetter = new ArrayList<String>();
        ArrayList<String> templistNoLetter = new ArrayList<String>();


        if (wordlist.length == 1) {
            search_correct_letter(activity, letter);
        } else {

            // split list on letter occurrence true/false excluding double occurrence
            for (int i = 0; i < wordlist.length; i++) {
                if ((wordlist[i].indexOf(letter)) >= 0) {
                    if ((wordlist[i].length() - wordlist[i].replace(letterstring, "").length()) == 1) {
                        templistLetter.add(wordlist[i]);
                    }
                } else {
                    templistNoLetter.add(wordlist[i]);
                }
            }

            // check if list of letter at any index is larger than non letter list
            if ((templistLetter.size() >= templistNoLetter.size()) && templistLetter.size() > 0) {
                String[] temp_wordlist = templistLetter.toArray(new String[templistLetter.size()]);

                for (int i = 0; i < wordlength; i++) {
                    ArrayList<String> subTempList = new ArrayList<String>();
                    for (int j = 0; j < temp_wordlist.length; j++) {
                        if (temp_wordlist[j].charAt(i) == letter) {
                            subTempList.add(temp_wordlist[j]);
                        }
                    }
                    if (subTempList.size() >= templistNoLetter.size()) {
                        templistNoLetter = subTempList;
                        correct_letter = true;
                    }
                }

                wordlist = templistNoLetter.toArray(new String[templistNoLetter.size()]);
                word = wordlist[new Random().nextInt(wordlist.length)];
                search_correct_letter(activity, letter);

            } else {
                wordlist = templistNoLetter.toArray(new String[templistNoLetter.size()]);
                word = wordlist[new Random().nextInt(wordlist.length)];
            }
        }

        if (correct_letter) {
            TextView answer_view = (TextView) activity.findViewById(R.id.in_game_answer);
            answer_view.setText(answer);
        } else {
            wrongGuess(letter, activity);
        }
    }
}
