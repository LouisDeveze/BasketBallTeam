package fr.android.basketballteam.team;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fr.android.basketballteam.R;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public class FragmentTeamAdder extends Fragment {

    /** Activity reference */
    private TeamFragListener activityMain;
    /** Team Name Edit Text */
    private EditText teamName;
    /** Team City Edit Text */
    private EditText teamCity;
    /** Player Name Edit Text */
    private EditText playerName;
    /** Player Jersey Edit Text */
    private EditText playerJersey;
    /** Spinner Player Team */
    private Spinner playerTeam;
    /** Floating Button Add Team */
    private FloatingActionButton addTeam;
    /** Floating Button Add Player */
    private FloatingActionButton addPlayer;

    /** Constructor */
    public FragmentTeamAdder(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_team_adder, container, false);
        // Retrieve Views
        teamName = view.findViewById(R.id.edit_text_team_name);
        teamCity = view.findViewById(R.id.edit_text_team_city);
        playerName = view.findViewById(R.id.edit_text_player_name);
        playerJersey = view.findViewById(R.id.edit_text_player_jersey);
        addTeam = view.findViewById(R.id.team_add_button);
        addPlayer = view.findViewById(R.id.player_add_button);
        playerTeam = view.findViewById(R.id.spinner_player_team);

        new AsyncTeamSpinnerLoader(playerTeam, getActivity()).execute();

        // Create Listeners
        addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = teamName.getText().toString();
                String strCity = teamCity.getText().toString();
                if(strName.matches("^[a-zA-Z \\-\\.\\']*$") && strCity.matches("^[a-zA-Z \\-\\.\\']*$")){
                    Team team = new Team(0, strName, strCity, null );
                    activityMain.onFragmentTeamAdded(team);
                }else{
                    Toast.makeText(getContext(), R.string.invalid_entry, Toast.LENGTH_SHORT).show();
                    Log.d("INVALID ENTRY", "TEAM");
                }
            }
        });

        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = playerName.getText().toString();
                String strJersey = playerJersey.getText().toString();
                String strTeam= playerTeam.getSelectedItem().toString();
                if(strName.matches("^[a-zA-Z \\-\\.\\']*$") && strJersey.matches("[0-9]+")){
                    int intJersey = Integer.parseInt(strJersey);
                    Player player = new Player(0, intJersey,strName,0);
                    activityMain.onFragmentPlayerAdded(player, strTeam);
                }else{
                    Toast.makeText(getContext(), R.string.invalid_entry, Toast.LENGTH_SHORT).show();
                    Log.d("INVALID ENTRY", "PLAYER");
                }
            }
        });


        // View Creation
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TeamFragListener) {
            activityMain = (TeamFragListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement TeamFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityMain = null;
    }


}
