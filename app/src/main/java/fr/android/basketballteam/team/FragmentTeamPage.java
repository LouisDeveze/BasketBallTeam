package fr.android.basketballteam.team;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import fr.android.basketballteam.R;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public class FragmentTeamPage extends Fragment {

    /** Team displayed by the Fragment */
    private Team team;
    /** Team Title Layout */
    private TextView title;
    /** Team Players Layout */
    private LinearLayout players;
    /** Team jerjey Layout */
    private LinearLayout jersey;


    public FragmentTeamPage(){
        Log.d("FRAGMENT_TEAM_PAGE", "Construct"  + this.toString());
    }

    public static FragmentTeamPage newInstance(Team team){

        Log.d("FRAGMENT_TEAM_PAGE", "Is Safe !");
        FragmentTeamPage frag = new FragmentTeamPage();
        frag.setTeam(team);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("FRAGMENT_TEAM_PAGE", "Create View" + this.toString());
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.team_page, container, false);

        Log.d("ViewGroup", view.toString());

        // Retrieving views
        title = view.findViewById(R.id.team_name);
        players = view.findViewById(R.id.players_name);
        jersey = view.findViewById(R.id.players_jersey);

        if(team != null){
            title.setText(team.city() + " " + team.name());
            Typeface confortaa_bold = title.getTypeface();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.RIGHT;


            for (Player player : team.players()) {
                TextView playerName = new TextView(getContext());
                playerName.setText(player.name());
                playerName.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
                playerName.setTextSize(18);
                playerName.setSingleLine(true);
                playerName.setTypeface(confortaa_bold);
                players.addView(playerName);

                TextView jerseyNumber = new TextView(getContext());
                jerseyNumber.setText(String.valueOf(player.number()));
                jerseyNumber.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
                jerseyNumber.setTextSize(18);
                jerseyNumber.setTypeface(confortaa_bold);
                jerseyNumber.setLayoutParams(params);
                jersey.addView(jerseyNumber);

            }
        }
        // View Creation
        return view;
    }


    public void setTeam(Team team){
        this.team = team;
    }

}
