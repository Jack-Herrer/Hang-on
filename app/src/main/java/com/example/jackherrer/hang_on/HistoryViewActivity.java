package com.example.jackherrer.hang_on;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

public class HistoryViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view_activity);

        if(this.getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            boolean won = extras.getBoolean("won", false);

            // only view name entry + submit if game is won
            if (won) {
                EditText name_entry_button = (EditText) findViewById(R.id.hisotry_name_entry_view);
                Button submit_button = (Button) findViewById(R.id.btnAddItem);

                name_entry_button.setVisibility(View.VISIBLE);
                submit_button.setVisibility(View.VISIBLE);
            }
        }

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;

    public void onAddItem(View view) {
        Bundle extras = getIntent().getExtras();
        int lives = extras.getInt("lives");
        String word = extras.getString("word");
        int mistakes = extras.getInt("mistakes");

        EditText name_entry_button = (EditText) findViewById(R.id.hisotry_name_entry_view);
        Button submit_button = (Button) findViewById(R.id.btnAddItem);

        String itemText = name_entry_button.getText().toString() + "\nword:" + word + "\nmistakes: " + mistakes + "\n"
                + lives + " lives left";
        itemsAdapter.add(itemText);
        name_entry_button.setText("");
        writeItems();

        name_entry_button.setVisibility(view.INVISIBLE);
        submit_button.setVisibility(view.INVISIBLE);

    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }
                });
    }

}
