package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Gameplay {

    int lives;
    String answer = "";
    String[] wordlist;
    String word;
    String guessed = "";
    boolean correct_letter = false;

    public void initiate_blank_spaces(Activity activity){

        // get wordlength setting
        SharedPreferences settings = activity.getSharedPreferences("prefs_settings", Context.MODE_PRIVATE);
        int wordlength = settings.getInt("wordlength", 5);

        // place dot on blank space
        for(int i = 0; i <wordlength; i++){
            answer+=".";
            TextView answer_view = (TextView) activity.findViewById(R.id.in_game_answer);
            answer_view.setText(answer);
        }
    }

    public String[] loadwordlist (Activity activity){
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

    public void wrong_guess(char letter, Activity activity){
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

    public void on_win(Activity activity){
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
                    on_win(activity);
                }
            }
        }
    }

//    public void onInGameEnter(Activity activity){
//        Toast.makeText(activity,""+lives, Toast.LENGTH_LONG).show();


//        GoodGameplay goodGameplay_class = new GoodGameplay();
//        goodGameplay_class.handle_input(activity, lives);

//        EditText answer_box = (EditText)activity.findViewById(R.id.in_game_answer_box);
//        String answer_letters = String.valueOf(answer_box.getText());
//
//        //check for valid input
//        if((answer_letters.length()==1) && (lives != 0) && (!answer.equals(word))) {
//            char letter = answer_letters.charAt(0);
//
//            boolean correct_letter = false;
//
//            // check if letter is in word and update answer
//            for(int i = 0; i < word.length(); i++){
//                if(word.charAt(i) == letter){
//
//                    Toast.makeText(activity, "Bingo", Toast.LENGTH_SHORT).show();
//
//                    char[] myNameChars = answer.toCharArray();
//                    myNameChars[i] = letter;
//                    answer = String.valueOf(myNameChars);
//
//                    correct_letter = true;
//
//                    // check on win
//                    if(answer.equals(word)) {
//                        Toast.makeText(activity, "You win!", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            if (correct_letter){
//                TextView answer_view = (TextView)activity.findViewById(R.id.in_game_answer);
//                answer_view.setText(answer);
//            }
//
//            //update lives in case of wrong letter
//            else{
//                lives--;
//                TextView lives_view = (TextView)activity.findViewById(R.id.in_game_lives);
//                lives_view.setText("Lives: " + lives);
//
//                //check if lives left
//                if(lives == 0){
//                    Toast.makeText(activity,"Game over: You lost", Toast.LENGTH_LONG).show();
//
//                }
//            }
//
//        }
//
//        // toast invalid input
//        else if(lives == 0){
//            Toast.makeText(activity,"Game over: You lost", Toast.LENGTH_LONG).show();
//        }
//
//        else if(answer.equals(word)){
//            Toast.makeText(activity,"You win!", Toast.LENGTH_LONG).show();
//        }
//
//        else{Toast.makeText(activity,"invalid input", Toast.LENGTH_SHORT).show();}
//

    //}


}
