package fr.android.basketballteam.model;

import java.sql.Date;

public class Match {

    /** Match Id */
    private int id;
    /** First Team */
    private Team first;
    /** Second Team */
    private Team second;
    /** Winning Team */
    private Team winner;
    /** Score of the First Team */
    private int scoreFirst;
    /** Score of the Second Team */
    private int scoreSecond;
    /** Latitude of the Match stadium */
    private double latitude;
    /** Lonigitude of the Match stadium */
    private double longitude;
    /** Date of the match */
    private Date date;

    /**
     * Constructor of the Match Class
     * @param id of the Match
     * @param first team of the Match
     * @param second team of the Match
     * @param scoreFirst score of the first team of the Match
     * @param scoreSecond score of the second team of the Match
     * @param latitude location of the match
     * @param longitude location of the match
     * @param date of the match
     */
    public Match(int id, Team first, Team second, int scoreFirst, int scoreSecond, double latitude, double longitude, Date date){

        this.id = id;
        this.first = first;
        this.second = second;
        this.scoreFirst = scoreFirst;
        this.scoreSecond = scoreSecond;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.winner = scoreFirst > scoreSecond ? this.first : this.second;
    }

    /** @return match id */
    public int id(){return id;}
    /** @return match first team */
    public Team first(){return first;}
    /** @return match second team */
    public Team second(){return second;}
    /** @return score of the first team */
    public int scoreFirst(){return scoreFirst;}
    /** @return score of the second team */
    public int scoreSecond(){return scoreSecond;}
    /** @return the winning team */
    public Team winner(){return winner;}
    /** @return the date of the match */
    public Date date(){return date;}
    /** @return latitude */
    public double latitude(){return latitude;}
    /** @return longitude */
    public double longitude(){return longitude;}
    
    @Override
    public String toString() {
    	return "Match " + id + ": " + first.name() + " vs " + second.name() + "  | Winner is: " + winner.name() + " " + scoreFirst + "-" + scoreSecond + " at " + date.toString() + " " + latitude + " " + longitude; 
    }
    
}
