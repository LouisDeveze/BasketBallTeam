package fr.android.basketballteam.match;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fr.android.basketballteam.R;
import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.model.Action;
import fr.android.basketballteam.model.Match;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public class AsyncMatchPageLoader extends AsyncTask<Integer, Integer, ArrayList<Action>> {

    private MatchFragListener activityMain;
    private LinearLayout matchContent;
    private LayoutInflater inflater;
    private Match match = null;
    private int match_id;

    private TextView first;
    private TextView second;
    private TextView scoreFirst;
    private TextView scoreSecond;

    private int blue;
    private int gold;

    public AsyncMatchPageLoader(LinearLayout matchesContent, LayoutInflater inflater, MatchFragListener listener, int match_id,
                                TextView first, TextView second, TextView scoreFirst, TextView scoreSecond, int gold, int blue ){
        this.matchContent = matchesContent;

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
    protected ArrayList<Action> doInBackground(Integer... integers) {
        DatabaseManager manager = new DatabaseManager();
        ArrayList<Player> players = manager.requestPlayers();
        ArrayList<Team> teams = manager.requestTeams(players);
        ArrayList<Match> matches = manager.requestMatches(teams);
        ArrayList<Action> actions = manager.requestActions(players, teams, matches);

        for (Match m: matches) {
            if(m.id() == match_id){
                match = m;
            }
        }

        return actions;
    }

    @Override
    protected void onPostExecute(ArrayList<Action> actions){


        // Show Views
        first.setText(match.first().name());
        second.setText(match.second().name());
        scoreFirst.setText(String.valueOf(match.scoreFirst()));
        scoreSecond.setText(String.valueOf(match.scoreSecond()));

        for(final Action action : actions){
            if(action.match().id() == match_id){
                LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.match_page_item, null);
                // Retrieve View
                AppCompatTextView player = layout.findViewById(R.id.action_player);
                AppCompatTextView score = layout.findViewById(R.id.action_score);
                AppCompatTextView time = layout.findViewById(R.id.action_time);
                AppCompatTextView faults = layout.findViewById(R.id.action_faults);

                if(action.team().id() == match.first().id()){
                    // Player
                    player.setText(action.player().name());
                    player.setTextColor(gold);
                    // Score
                    score.setText(String.valueOf(action.score()));
                    String min = String.valueOf(action.time()/60);
                    String sec = String.valueOf(action.time()%60);
                    String timeStr = min + "m:"+sec+"s";
                    // Time
                    time.setText(timeStr);
                    // Faults
                    faults.setText(String.valueOf(action.faults()));

                }else if(action.team().id() == match.second().id()){
                    player.setText(action.player().name());
                    player.setTextColor(blue);

                    // Score
                    score.setText(String.valueOf(action.score()));
                    String min = String.valueOf(action.time()/60);
                    String sec = String.valueOf(action.time()%60);
                    String timeStr = min + "m:"+sec+"s";
                    // Time
                    time.setText(timeStr);
                    // Faults
                    faults.setText(String.valueOf(action.faults()));
                }
                matchContent.addView(layout);
            }
        }
    }
}
