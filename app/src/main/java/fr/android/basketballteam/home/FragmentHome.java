package fr.android.basketballteam.home;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.fragment.app.Fragment;

import fr.android.basketballteam.R;
import fr.android.basketballteam.match.MatchFragListener;

public class FragmentHome extends Fragment {

    private MatchFragListener activityMain;

    private LinearLayout table;

    /** Constructor */
    public FragmentHome(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        this.table = view.findViewById(R.id.matchTable);

        new AsyncSavedMatchLoader(getContext(), table, inflater, activityMain).execute();

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

}
