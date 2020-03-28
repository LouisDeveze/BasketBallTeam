package fr.android.basketballteam.mapping;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.model.Match;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public class AsyncMapIntent extends AsyncTask<Integer, Integer, Match> {

    private Context context;
    private int match_id;

    public AsyncMapIntent(Context context){
        this.context = context;
    }

    @Override
    protected Match doInBackground(Integer... integers) {
        this.match_id = integers[0];
        DatabaseManager manager = new DatabaseManager();
        ArrayList<Player> players = manager.requestPlayers();
        ArrayList<Team> teams = manager.requestTeams(players);
        ArrayList<Match> matches = manager.requestMatches(teams);

        Match match = null;
        for(Match m : matches){
            if(m.id() == match_id){
                match =  m;
            }
        }
        return match;
    }

    @Override
    protected void onPostExecute(Match match){
        Intent intent = new Intent(context, ActivityMap.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Latitude", match.latitude());
        intent.putExtra("Longitude", match.longitude());
        context.startActivity(intent);
    }
}
