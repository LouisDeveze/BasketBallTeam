package fr.android.basketballteam.model;
import java.util.ArrayList;

/**
 * This class stores the data from the database as the model of the application
 */
public class Team {

    /** Team ID */
    private int id;
    /** Team Name */
    private String name;
    /** Team Country */
    private String city;
    /** List of Players */
    private ArrayList<Player> players;

    /**
     * Constructor of the Team Object
     * @param id of the Team
     * @param name of the Team
     * @param city of origin of the Team
     * @param players composing the Team
     */
    public Team(int id, String name, String city, ArrayList<Player> players){
        this.id = id;
        this.name = name;
        this.city = city;
        this.players = players;
    }

    /** @return the id of the Team */
    public int id(){return id;}
    /** @return the name of the Team */
    public String name(){return name;}
    /** @return the city of origin of the Team */
    public String city(){return city;}
    /** @return the players list of the Team */
    public ArrayList<Player> players(){return players;}
    
    @Override
    public String toString() {
    	return "Team " + id + ": " + city +" "+ name +" with " + players.size() + " players";
    }

}
