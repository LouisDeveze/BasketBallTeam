package fr.android.basketballteam.match;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import java.sql.Date;
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

public class AsyncMatchPageLoader extends AsyncTask<Integer, Integer, Boolean> {

    private MatchFragListener activityMain;
    private Context context;
    private LinearLayout matchContent;
    private LayoutInflater inflater;
    private int match_id;
    private Match match = null;
    private ArrayList<Action> actions;

    private MatchLite matchLite;
    private ArrayList<ActionLite> actionLite;


    private TextView first;
    private TextView second;
    private TextView scoreFirst;
    private TextView scoreSecond;

    private SQLiteDatabase db;

    private int blue;
    private int gold;

    public AsyncMatchPageLoader(Context context, LinearLayout matchesContent, LayoutInflater inflater, MatchFragListener listener, int match_id,
                                TextView first, TextView second, TextView scoreFirst, TextView scoreSecond, int gold, int blue) {
        this.matchContent = matchesContent;
        this.context = context;
        this.inflater = inflater;
        this.activityMain = listener;
        this.match_id = match_id;

        this.first = first;
        this.second = second;
        this.scoreFirst = scoreFirst;
        this.scoreSecond = scoreSecond;

        this.gold = gold;
        this.blue = blue;

    }

    @Override
    protected Boolean doInBackground(Integer... integers) {

        // Check if the match exists locally
        db = DatabaseOpenHelper.getInstance(context).getWritableDatabase();
        Cursor c = db.rawQuery("select * from matches;", null);

        Boolean isLocal = false;
        while(c.moveToNext()){
            if(c.getInt(c.getColumnIndex("m_id")) == match_id){
                isLocal = true;
                break;
            }
        }

        if (isLocal) {
            int m_id = c.getInt(c.getColumnIndex("m_id"));
            Date m_date = Date.valueOf(c.getString(c.getColumnIndex("m_date")));
            String m_first = c.getString(c.getColumnIndex("m_first_team"));
            String m_second = c.getString(c.getColumnIndex("m_second_team"));
            int m_score_first = c.getInt(c.getColumnIndex("m_score_first"));
            int m_score_second = c.getInt(c.getColumnIndex("m_score_second"));
            int m_latitude = c.getInt(c.getColumnIndex("m_latitude"));
            int m_longitude = c.getInt(c.getColumnIndex("m_longitude"));
            matchLite = new MatchLite(m_id, m_first, m_second, m_score_first, m_score_second, m_latitude, m_longitude, m_date);
            c.close();


            this.actionLite = new ArrayList<>();
            c = db.rawQuery("select * from actions;", null);
            while (c.moveToNext()) {

                int a_id = c.getInt(c.getColumnIndex("a_id"));
                String a_player = c.getString(c.getColumnIndex("a_player"));
                String a_team = c.getString(c.getColumnIndex("a_team"));
                int a_match = c.getInt(c.getColumnIndex("a_match"));
                int a_score = c.getInt(c.getColumnIndex("a_score"));
                int a_time = c.getInt(c.getColumnIndex("a_time"));
                int a_faults = c.getInt(c.getColumnIndex("a_faults"));
                actionLite.add(new ActionLite(a_id, a_player, a_team, a_match, a_score, a_time, a_faults));
            }
            c.close();

        }else {

            DatabaseManager manager = new DatabaseManager();
            ArrayList<Player> players = manager.requestPlayers();
            ArrayList<Team> teams = manager.requestTeams(players);
            ArrayList<Match> matches = manager.requestMatches(teams);
            this.actions = manager.requestActions(players, teams, matches);
            for (Match m : matches) {
                if (m.id() == match_id) {
                    match = m;
                }
            }
        }


        return isLocal;
    }

    @Override
    protected void onPostExecute(Boolean isLocal) {



        if (isLocal) {
            // Show Views
            first.setText(matchLite.first());
            second.setText(matchLite.second());
            scoreFirst.setText(String.valueOf(matchLite.scoreFirst()));
            scoreSecond.setText(String.valueOf(matchLite.scoreSecond()));

            for (final ActionLite action : actionLite) {
                if (action.match() == match_id) {

                    LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.match_page_item, null);
                    // Retrieve View
                    AppCompatTextView player = layout.findViewById(R.id.action_player);
                    AppCompatTextView score = layout.findViewById(R.id.action_score);
                    AppCompatTextView time = layout.findViewById(R.id.action_time);
                    AppCompatTextView faults = layout.findViewById(R.id.action_faults);

                    if (action.team().equals(matchLite.first())) {
                        // Player
                        player.setText(action.player());
                        player.setTextColor(gold);
                        // Score
                        score.setText(String.valueOf(action.score()));
                        String min = String.valueOf(action.time() / 60);
                        String sec = String.valueOf(action.time() % 60);
                        String timeStr = min + "m:" + sec + "s";
                        // Time
                        time.setText(timeStr);
                        // Faults
                        faults.setText(String.valueOf(action.faults()));

                    } else if (action.team().equals(matchLite.second())) {
                        player.setText(action.player());
                        player.setTextColor(blue);

                        // Score
                        score.setText(String.valueOf(action.score()));
                        String min = String.valueOf(action.time() / 60);
                        String sec = String.valueOf(action.time() % 60);
                        String timeStr = min + "m:" + sec + "s";
                        // Time
                        time.setText(timeStr);
                        // Faults
                        faults.setText(String.valueOf(action.faults()));
                    }
                    matchContent.addView(layout);
                }
            }
        } else {
            // Show Views
            first.setText(match.first().name());
            second.setText(match.second().name());
            scoreFirst.setText(String.valueOf(match.scoreFirst()));
            scoreSecond.setText(String.valueOf(match.scoreSecond()));

            for (final Action action : actions) {

                if (action.match().id() == match_id) {
                    Log.d("Test", "Cool");
                    LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.match_page_item, null);
                    // Retrieve View
                    AppCompatTextView player = layout.findViewById(R.id.action_player);
                    AppCompatTextView score = layout.findViewById(R.id.action_score);
                    AppCompatTextView time = layout.findViewById(R.id.action_time);
                    AppCompatTextView faults = layout.findViewById(R.id.action_faults);

                    if (action.team().id() == match.first().id()) {
                        // Player
                        player.setText(action.player().name());
                        player.setTextColor(gold);
                        // Score
                        score.setText(String.valueOf(action.score()));
                        String min = String.valueOf(action.time() / 60);
                        String sec = String.valueOf(action.time() % 60);
                        String timeStr = min + "m:" + sec + "s";
                        // Time
                        time.setText(timeStr);
                        // Faults
                        faults.setText(String.valueOf(action.faults()));

                    } else if (action.team().id() == match.second().id()) {
                        player.setText(action.player().name());
                        player.setTextColor(blue);

                        // Score
                        score.setText(String.valueOf(action.score()));
                        String min = String.valueOf(action.time() / 60);
                        String sec = String.valueOf(action.time() % 60);
                        String timeStr = min + "m:" + sec + "s";
                        // Time
                        time.setText(timeStr);
                        // Faults
                        faults.setText(String.valueOf(action.faults()));
                    }
                    matchContent.addView(layout);
                }
            }
        }
        db.close();
    }
}
