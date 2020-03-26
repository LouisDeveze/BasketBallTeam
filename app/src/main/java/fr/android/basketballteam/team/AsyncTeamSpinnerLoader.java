package fr.android.basketballteam.team;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import fr.android.basketballteam.R;
import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.model.Team;

public class AsyncTeamSpinnerLoader extends AsyncTask<Integer, Integer, ArrayList<String>>{

    private Spinner spinner;
    private FragmentActivity reference;

    public AsyncTeamSpinnerLoader(Spinner spinner, FragmentActivity reference){
        this.spinner = spinner;
        this.reference = reference;
    }

    @Override
    protected ArrayList<String> doInBackground(Integer... integers) {
        DatabaseManager manager = new DatabaseManager();
        ArrayList<Team> teams =  manager.requestTeamsNames();
        ArrayList<String> list= new ArrayList<String>();
        for ( Team team :teams) {
            list.add(team.name());
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<String> teams){
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(reference, R.layout.spinner_item, teams);
        // Drop down layout style – list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
}
