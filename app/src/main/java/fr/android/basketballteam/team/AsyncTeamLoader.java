package fr.android.basketballteam.team;

import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public class AsyncTeamLoader extends AsyncTask<Integer, Integer, ArrayList<Team>> {

    private TeamPageAdapter adapter;
    private ViewPager teamPager;

    public AsyncTeamLoader(TeamPageAdapter adapter, ViewPager teamPager) {
        this.adapter = adapter;
        this.teamPager = teamPager;
    }

    @Override
    protected ArrayList<Team> doInBackground(Integer... ints) {
        DatabaseManager manager = new DatabaseManager();
        ArrayList<Player> players = manager.requestPlayers();
        return manager.requestTeams(players);
    }

    @Override
    protected void onPostExecute(ArrayList<Team> teams) {
        for (int i = 0; i < teams.size(); i++) {
            FragmentTeamPage page = FragmentTeamPage.newInstance(teams.get(i));
            adapter.addPage(page);
        }
        teamPager.setAdapter(adapter);
    }
}
