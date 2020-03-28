package fr.android.basketballteam.match;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fr.android.basketballteam.R;
import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.database.DatabaseOpenHelper;
import fr.android.basketballteam.model.Action;
import fr.android.basketballteam.model.ActionLite;
import fr.android.basketballteam.model.Match;
import fr.android.basketballteam.model.MatchLite;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public class AsyncLocalSave extends AsyncTask<Integer, Integer, Boolean> {

    private Context context;
    private MatchLite match;
    private SQLiteDatabase db;
    private FloatingActionButton download;

    public AsyncLocalSave(Context context, FloatingActionButton download){
        this.context = context;
        this.match = null;
        this.download = download;
    }

    @Override
    protected Boolean doInBackground(Integer... match_id) {
        // Get Databases
        DatabaseManager manager = new DatabaseManager();
        db = DatabaseOpenHelper.getInstance(context).getWritableDatabase();

        ArrayList<Player> players = manager.requestPlayers();
        ArrayList<Team> teams = manager.requestTeams(players);
        ArrayList<Match> matches = manager.requestMatches(teams);
        ArrayList<Action> actions = manager.requestActions(players, teams, matches);

        for (Match m: matches) {
            if(m.id() == match_id[0]){
                match = new MatchLite(m.id(), m.first().name(), m.second().name(), m.scoreFirst(), m.scoreSecond(), m.latitude(),m.longitude(),m.date());
                ContentValues values = new ContentValues();
                values.put("m_id", match.id());
                values.put("m_date", match.date().toString());
                values.put("m_first_team", match.first());
                values.put("m_second_team", match.second());
                values.put("m_score_first", match.scoreFirst());
                values.put("m_score_second", match.scoreSecond());
                values.put("m_latitude", match.latitude());
                values.put("m_longitude", match.longitude());
                if(db.insert("matches",null,values) == -1){
                    Log.d("SQLite","Insertion Match Failed");
                }

            }
        }

        for(Action a : actions){
            if(a.match().id() == match_id[0]){
                ActionLite actionLite = new ActionLite(a.id(), a.player().name(), a.team().name(), a.match().id(), a.score(), a.time(), a.faults());
                Log.d("Inserting", actionLite.toString());
                ContentValues values = new ContentValues();
                values.put("a_id", actionLite.id());
                values.put("a_player", actionLite.player());
                values.put("a_team", actionLite.team());
                values.put("a_match", actionLite.match());
                values.put("a_score", actionLite.score());
                values.put("a_time", actionLite.time());
                values.put("a_faults", actionLite.faults());
                if(db.insert("actions",null,values) == -1){
                    Log.d("SQLite","Insertion Action Failed");
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean b){

        download.setImageDrawable(context.getResources().getDrawable(R.drawable.download_full, null));
        db.close();
    }
}
