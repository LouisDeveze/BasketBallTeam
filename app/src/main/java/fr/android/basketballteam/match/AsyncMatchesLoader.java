package fr.android.basketballteam.match;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;

import fr.android.basketballteam.R;
import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.model.Match;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public class AsyncMatchesLoader extends AsyncTask<Integer, Integer, ArrayList<Match>> {

    private MatchFragListener activityMain;
    private LinearLayout matchesContent;
    private LayoutInflater inflater;

    public AsyncMatchesLoader(LinearLayout matchesContent, LayoutInflater inflater, MatchFragListener listener){
        this.matchesContent = matchesContent;
        this.inflater = inflater;
        this.activityMain = listener;
    }

    @Override
    protected ArrayList<Match> doInBackground(Integer... integers) {
        DatabaseManager manager = new DatabaseManager();
        ArrayList<Player> players = manager.requestPlayers();
        ArrayList<Team> teams = manager.requestTeams(players);
        ArrayList<Match> matches = manager.requestMatches(teams);

        return matches;
    }

    @Override
    protected void onPostExecute(ArrayList<Match> matches){

        for(final Match match : matches){
            LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.match_item, null);
            layout.setPadding(25,25,25,0);
            // Retrieve Views
            AppCompatTextView firstTeam = layout.findViewById(R.id.first_team);
            AppCompatTextView secondTeam = layout.findViewById(R.id.second_team);
            AppCompatTextView score = layout.findViewById(R.id.score);
            AppCompatImageButton viewMatchButton = layout.findViewById(R.id.view_match_button);
            // Show Views
            firstTeam.setText(match.first().name());
            secondTeam.setText(match.second().name());
            score.setText(String.valueOf(match.scoreFirst()) + "-" + String.valueOf(match.scoreSecond()));
            viewMatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activityMain.onMatchPageFragmentSelected(match.id());
                }
            });
            matchesContent.addView(layout);
        }
    }
}
