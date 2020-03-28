package fr.android.basketballteam.model;

public class ActionLite {

    /** Action ID */
    private int id;
    /** Action Player */
    private String player;
    /** Action Team */
    private String team;
    /** Action Match */
    private int match;
    /** Score of the action */
    private int score;
    /** Number of time spent in game in seconds */
    private int time;
    /** Number of faults done by the player */
    private int faults;

    public ActionLite(int id, String player, String team, int match, int score, int time, int faults) {
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
    public String player(){return player;}
    /** @return Action Team */
    public String team(){return team;}
    /** @return Action Match */
    public int match(){return match;}
    /** @return Score of the action */
    public int score(){return score;}
    /** @return Number of time spent in game in seconds */
    public int time(){return time;}
    /** @return Number of faults done by the player */
    public int faults(){return faults;}

    @Override
    public String toString() {
        return "Action "+id + ": "+ player + " in " + team+ " played " + time + "sec, made " + faults + " faults and scored " + score;
    }

}
