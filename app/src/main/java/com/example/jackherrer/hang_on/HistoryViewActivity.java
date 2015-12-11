package com.example.jackherrer.hang_on;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

/**
 * HistoryViewActivity Class
 * This class handles the full history of winners
 *
 * @version 1
 * @author Michiel van der List  */

public class HistoryViewActivity extends AppCompatActivity {
    ActionMenuHandler actionMenuHandler_class = new ActionMenuHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // only view name entry + submit if game is won
        if(this.getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            boolean won = extras.getBoolean("won", false);

            if (won) {
                EditText name_entry_button = (EditText) findViewById(R.id.hisotry_name_entry_view);
                Button submit_button = (Button) findViewById(R.id.btnAddItem);
                Button play_button = (Button) findViewById(R.id.history_play_game_button);

                name_entry_button.setVisibility(View.VISIBLE);
                submit_button.setVisibility(View.VISIBLE);
                play_button.setVisibility(View.INVISIBLE);
            }
        }

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        return actionMenuHandler_class.handleMenu(item, this);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File historyFile = new File(filesDir, "history.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(historyFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File historyFile = new File(filesDir, "history.txt");
        try {
            FileUtils.writeLines(historyFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    public void onSubmit(View view) {
        Bundle extras = getIntent().getExtras();
        int lives = extras.getInt("lives");
        String word = extras.getString("word");
        int mistakes = extras.getInt("mistakes");

        EditText name_entry_button = (EditText) findViewById(R.id.hisotry_name_entry_view);
        Button submit_button = (Button) findViewById(R.id.btnAddItem);
        Button play_button = (Button) findViewById(R.id.history_play_game_button);

        String itemText = name_entry_button.getText().toString().toUpperCase() + " won with word: "
                + word + " and " + mistakes +  " mistakes" + " (" + lives + " lives left)";
        itemsAdapter.add(itemText);
        name_entry_button.setText("");
        writeItems();

        name_entry_button.setVisibility(view.INVISIBLE);
        submit_button.setVisibility(view.INVISIBLE);
        play_button.setVisibility(view.VISIBLE);
    }

    public void playGameClick(View view) {
        Intent new_game = new Intent(this, GameplayActivity.class);
        this.startActivity(new_game);
        this.finish();
    }
}
