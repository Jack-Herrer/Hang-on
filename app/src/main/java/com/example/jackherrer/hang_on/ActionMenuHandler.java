package com.example.jackherrer.hang_on;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

/**
 * ActionMenuHandler Class
 * This class handles the clicking on action bar buttons
 *
 * @version 1
 * @author Michiel van der List  */

public class ActionMenuHandler {

    public boolean handleMenu(MenuItem item, Activity activity) {

        switch (item.getItemId()) {
            case R.id.action_new_game:
                Intent new_game = new Intent(activity, GameplayActivity.class);
                activity.startActivity(new_game);
                activity.finish();
                return true;

            case R.id.action_history_view:
                Intent to_history= new Intent(activity, HistoryViewActivity.class);
                activity.startActivity(to_history);
                activity.finish();
                return true;

            case R.id.action_settings:
                Intent to_settings = new Intent(activity, SettingsActivity.class);
                activity.startActivity(to_settings);
                activity.finish();
                return true;

            case R.id.action_exit:
                activity.finish();
                return true;

            default:
                return true;
        }
    }
}
