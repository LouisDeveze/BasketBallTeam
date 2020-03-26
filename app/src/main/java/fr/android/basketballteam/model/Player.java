package fr.android.basketballteam.model;
/**
 * This class holds the data from the database about a player
 */
public class Player {

    /** Player ID */
    private int id;
    /** Number of the Player in team */
    private int number;
    /** Name of the Player */
    private String name;
    /** Team ID of the Player */
    private int team;

    /**
     * Player Constructor
     * @param id of the player
     * @param number number of the player in team
     * @param name of the player
     * @param team id
     */
    public Player(int id, int number, String name, int team){
        this.id = id;
        this.number = number;
        this.name = name;
        this.team = team;
    }

    /** @return Player's ID */
    public int id(){return id;}
    /** @return Player's Number in the Team */
    public int number(){return number;}
    /** @return Player's Name */
    public String name(){return name;}
    /** @return Player's Team ID */
    public int team(){return team;}
    
    @Override
    public String toString() {
    	return "Player " + id + ": " + name + " | Team: " + team + " | number: " + number;  
    }
}
