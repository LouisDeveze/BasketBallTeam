package fr.android.basketballteam.match;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fr.android.basketballteam.R;
import fr.android.basketballteam.model.Match;

public class FragmentMatchPage extends Fragment {


    private MatchFragListener activityMain;

    private int match_id;

    private LinearLayout matchContent;

    /** Constructor */
    public FragmentMatchPage(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_match_page, container, false);

        this.matchContent = view.findViewById(R.id.matchContent);

        // Retrieve Views
        AppCompatTextView firstTeam = view.findViewById(R.id.match_team_first);
        AppCompatTextView secondTeam = view.findViewById(R.id.match_team_second);
        AppCompatTextView scoreFirst = view.findViewById(R.id.match_score_first);
        AppCompatTextView scoreSecond = view.findViewById(R.id.match_score_second);

        final FloatingActionButton download = view.findViewById(R.id.download);
        FloatingActionButton camera = view.findViewById(R.id.camera);
        FloatingActionButton pin = view.findViewById(R.id.pin);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Download
                onMatchDownload(match_id);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Download
                activityMain.onMatchCamera(match_id);
            }
        });

        pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pin
                activityMain.onMatchPin(match_id);
            }
        });

        int gold = ContextCompat.getColor(getContext(), R.color.colorAccentLight);
        int blue = ContextCompat.getColor(getContext(), R.color.colorSecondLight);

        if(this.match_id != 0){
            new AsyncMatchPageLoader(this.matchContent, getLayoutInflater(), activityMain, match_id, firstTeam, secondTeam, scoreFirst, scoreSecond, gold, blue).execute();
        }

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
        if (context instanceof MatchFragListener) {
            activityMain = (MatchFragListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement MatchFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityMain = null;
    }

    public void setMatch(int id){
        this.match_id = id;
    }

    public void onMatchDownload(int id){
        // Download on sqlite
    }
}
