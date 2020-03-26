package fr.android.basketballteam.team;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.model.Team;

public class AsyncTeamInsert extends AsyncTask<Team, Integer, Boolean> {

    @Override
    protected Boolean doInBackground(Team... teams) {
        DatabaseManager manager = new DatabaseManager();
        manager.insertTeam(teams[0]);
        return true;
    }

}
