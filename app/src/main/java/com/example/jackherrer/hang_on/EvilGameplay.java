package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class EvilGameplay extends Gameplay {

    public void handle_input (Activity activity, char ans) {
        SharedPreferences settings = activity.getSharedPreferences("prefs_settings", Context.MODE_PRIVATE);
        int wordlength = settings.getInt("wordlength", 5);
        char letter = ans;
        String letterstring = "" + letter;
        ArrayList<String> templist_good = new ArrayList<String>();
        ArrayList<String> templist_false = new ArrayList<String>();
        correct_letter = false;

        if (wordlist.length == 1) {
           // search_correct_letter(activity);
            search_correct_letter(activity, letter);
        }

        else {
            // split list on letter occurrence true/false excluding double occurrence
            for (int i = 0; i < wordlist.length; i++) {
                if ((wordlist[i].indexOf(letter)) >= 0) {
                    if ((wordlist[i].length() - wordlist[i].replace(letterstring, "").length()) == 1) {
                        templist_good.add(wordlist[i]);
                    }
                }
                else {
                    templist_false.add(wordlist[i]);
                }
            }

            //"correct guess"
            if ((templist_good.size() >= templist_false.size()) && templist_good.size() > 0) {
                String[] temp_wordlist = templist_good.toArray(new String[templist_good.size()]);

                for (int i = 0; i < wordlength; i++) {
                    ArrayList<String> subtemplist_good = new ArrayList<String>();
                    for (int j = 0; j < temp_wordlist.length; j++) {
                        if (temp_wordlist[j].charAt(i) == letter) {
                            subtemplist_good.add(temp_wordlist[j]);

                        }
                    }
                    if (subtemplist_good.size() >= templist_false.size()) {
                        templist_false = subtemplist_good;
                        correct_letter = true;
                    }
                }
                wordlist = templist_false.toArray(new String[templist_false.size()]);
                word = wordlist[new Random().nextInt(wordlist.length)];

                //find correct letter in chosen word
                search_correct_letter(activity, letter);
            }

            else {
                wordlist = templist_false.toArray(new String[templist_false.size()]);
                word = wordlist[new Random().nextInt(wordlist.length)];
            }
//            Toast.makeText(activity, "chosen word: " + word, Toast.LENGTH_LONG).show();
        }
        if (correct_letter) {
            TextView answer_view = (TextView) activity.findViewById(R.id.in_game_answer);
            answer_view.setText(answer);
        }

        else {
            wrong_guess(letter, activity);
        }
    }
}
