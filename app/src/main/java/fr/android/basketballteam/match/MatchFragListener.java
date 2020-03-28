package fr.android.basketballteam.match;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public interface MatchFragListener {
    void onMatchPageFragmentSelected(int id);
    void onMatchCamera(int id);
    void onMatchDownload(int id, FloatingActionButton download);
    void onMatchPin(int id);
}
