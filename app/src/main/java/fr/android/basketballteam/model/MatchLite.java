package fr.android.basketballteam.model;

import java.sql.Date;

public class MatchLite {

    /** Match Id */
    private int id;
    /** First Team */
    private String first;
    /** Second Team */
    private String second;
    /** Winning Team */
    private String winner;
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
    public MatchLite(int id, String first, String second, int scoreFirst, int scoreSecond, double latitude, double longitude, Date date){

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
    public String first(){return first;}
    /** @return match second team */
    public String second(){return second;}
    /** @return score of the first team */
    public int scoreFirst(){return scoreFirst;}
    /** @return score of the second team */
    public int scoreSecond(){return scoreSecond;}
    /** @return the winning team */
    public String winner(){return winner;}
    /** @return the date of the match */
    public Date date(){return date;}
    /** @return latitude */
    public double latitude(){return latitude;}
    /** @return longitude */
    public double longitude(){return longitude;}

    @Override
    public String toString() {
        return "Match " + id + ": " + first + " vs " + second + "  | Winner is: " + winner + " " + scoreFirst + "-" + scoreSecond + " at " + date.toString() + " " + latitude + " " + longitude;
    }

}
