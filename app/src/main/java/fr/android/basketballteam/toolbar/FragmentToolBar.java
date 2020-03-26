package fr.android.basketballteam.toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import fr.android.basketballteam.R;

public class FragmentToolBar extends Fragment {

    private ImageButton gallery;
    private ImageButton team;
    private ImageButton home;
    private ImageButton match;
    private ImageButton options;

    /** Reference to the listener */
    private ToolbarFragListener activityMain;

    /** Constructor */
    public FragmentToolBar(){}

    /** Option Menu Click Checks which button of the toolbar has been clicked and update the
     * fragment UI before sending the selected item to the main activity */
    private void onOptionMenuClick(View view){
        switch(view.getId()){
            case R.id.gallery_button:
                activityMain.onFragmentGallerySelected();
                break;
            case R.id.team_button:
                activityMain.onFragmentTeamSelected();
                break;
            case R.id.home_button:
                activityMain.onFragmentHomeSelected();
                break;
            case R.id.match_button:
                activityMain.onFragmentMatchSelected();
                break;
            case R.id.options_button:
                activityMain.onFragmentMapSelected();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_toolbar, container, false);

        // Retrieve views
        try{
            this.gallery = view.findViewById(R.id.gallery_button);
            this.team = view.findViewById(R.id.team_button);
            this.home = view.findViewById(R.id.home_button);
            this.match = view.findViewById(R.id.match_button);
            this.options = view.findViewById(R.id.options_button);
        }catch(NullPointerException n){
            n.printStackTrace();
        }

        // Create View Listener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionMenuClick(v);
            }
        };

        // Adding Listener to the views
        this.gallery.setOnClickListener(listener);
        this.team.setOnClickListener(listener);
        this.home.setOnClickListener(listener);
        this.match.setOnClickListener(listener);
        this.options.setOnClickListener(listener);

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
        if (context instanceof ToolbarFragListener) {
            activityMain = (ToolbarFragListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement ToolbarFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityMain = null;
    }

}
