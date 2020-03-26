package fr.android.basketballteam.match;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import fr.android.basketballteam.R;
import fr.android.basketballteam.home.HomeFragListener;

public class FragmentMatch extends Fragment {

    private MatchFragListener activityMain;

    private LinearLayout matchesContent;

    /** Constructor */
    public FragmentMatch(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_match, container, false);

        this.matchesContent = view.findViewById(R.id.matchContent);
        new AsyncMatchesLoader(this.matchesContent, inflater, activityMain).execute();

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
