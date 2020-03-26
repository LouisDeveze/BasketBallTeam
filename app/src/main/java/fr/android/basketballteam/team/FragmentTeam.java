package fr.android.basketballteam.team;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fr.android.basketballteam.R;

public class FragmentTeam extends Fragment {


    private TeamFragListener activityMain;

    private ViewPager teamPager;
    private TeamPageAdapter adapter;
    private FloatingActionButton teamAdder;

    /** Constructor */
    public FragmentTeam(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Log.d("FRAGMENT_TEAM", "CreateView"  + this.toString());
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_team, container, false);

        // Retrieve Views
        teamPager = view.findViewById(R.id.teamViewPager);
        teamAdder = view.findViewById(R.id.team_plus_button);
        // Add Listener
        teamAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityMain.onFragmentTeamAdderSelected();
            }
        });

        adapter = new TeamPageAdapter(this.getChildFragmentManager());
        AsyncTeamLoader loader = new AsyncTeamLoader(adapter, teamPager);
        loader.execute();
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
