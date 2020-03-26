package fr.android.basketballteam.model;

public class Action {

    /** Action ID */
    private int id;
    /** Action Player */
    private Player player;
    /** Action Team */
    private Team team;
    /** Action Match */
    private Match match;
    /** Score of the action */
    private int score;
    /** Number of time spent in game in seconds */
    private int time;
    /** Number of faults done by the player */
    private int faults;

    public Action(int id, Player player, Team team, Match match, int score, int time, int faults) {
        this.id = id;
        this.player = player;
        this.team = team;
        this.match = match;
        this.score = score;
        this.time = time;
        this.faults = faults;
    }

    /** @return action ID */
    public int id(){return id;}
    /** @return Action Player */
    public Player player(){return player;}
    /** @return Action Team */
    public Team team(){return team;}
    /** @return Action Match */
    public Match match(){return match;}
    /** @return Score of the action */
    public int score(){return score;}
    /** @return Number of time spent in game in seconds */
    public int time(){return time;}
    /** @return Number of faults done by the player */
    public int faults(){return faults;}
    
    @Override
    public String toString() {
		return "Action "+id + ": "+ player.name() + " in " + team.name() + " played " + time + "sec, made " + faults + " faults and scored " + score + " during the " + match.toString();
    }

}
