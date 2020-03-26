package fr.android.basketballteam.team;

import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public interface TeamFragListener {

    void onFragmentTeamAdderSelected();
    void onFragmentTeamAdded(Team team);
    void onFragmentPlayerAdded(Player p, String Team);

}
