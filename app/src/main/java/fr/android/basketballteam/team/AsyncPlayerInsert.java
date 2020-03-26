package fr.android.basketballteam.team;

import android.os.AsyncTask;

import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.model.Player;

public class AsyncPlayerInsert extends AsyncTask<Integer, Integer, Boolean> {

    private Player player;
    private String team;

    public AsyncPlayerInsert(Player player, String team){
        this.player = player;
        this.team = team;
    }

    @Override
    protected Boolean doInBackground(Integer... integers) {

        DatabaseManager manager = new DatabaseManager();
        manager.insertPlayer(player, team);

        return true;
    }
}
