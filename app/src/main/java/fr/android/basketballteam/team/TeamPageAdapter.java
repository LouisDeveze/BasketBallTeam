package fr.android.basketballteam.team;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TeamPageAdapter extends FragmentPagerAdapter {

    private ArrayList<FragmentTeamPage> pages;

    public TeamPageAdapter(FragmentManager manager){
        super(manager);
        this.pages = new ArrayList<>();
    }

    public void addPage(FragmentTeamPage page){
        synchronized (pages){
            this.pages.add(page);
        }
    }

    @Override
    public int getCount() {
        return this.pages.size();
    }

    @Override
    public Fragment getItem(int position) {
        return this.pages.get(position);
    }
}
