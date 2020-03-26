package fr.android.basketballteam.database;
import android.util.Log;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.android.basketballteam.model.Action;
import fr.android.basketballteam.model.Match;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public class DatabaseManager {

	// JDBC driver name and database URL
	private static final String IP = "192.168.1.42";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://10.0.2.2/BasketBall";

	//  Database credentials
	private static final String USER = "root";
	private static final String PASS = "";

	// Player Request
	private static final String PLAYER_REQUEST = "select * from players";
	// Team Request
	private static final String TEAM_REQUEST = "select * from teams";
	// Match Request
	private static final String MATCH_REQUEST = "select * from matches inner join (select t1.m_id, t1.score as m_score_first, t2.score as m_score_second from (select m_id, sum(a_score) as score from matches inner join actions on m_id = a_match where m_first_team = a_team group by m_id) t1 inner join (select m_id, sum(a_score) as score from matches inner join actions on m_id = a_match where m_second_team = a_team group by m_id) t2 on t1.m_id = t2.m_id) res on matches.m_id = res.m_id";
	// Actions Requests
	private static final String ACTION_REQUEST = "select * from actions";

	// Team Insert
	private static final String TEAM_INSERT = "insert into teams values(?,?,?)";
	// Player Insert
	private static final String PLAYER_INSERT = "insert into players values(?,?,?,?)";

	private Connection connection;

	public DatabaseManager() {
	}

	private void connect() {

		try{
			//STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			//STEP 3: Open a connection
			this.connection = DriverManager.getConnection(DB_URL,USER,PASS);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			this.connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Get all the Players of the Database */
	public ArrayList<Player> requestPlayers(){

		ArrayList<Player> players = new ArrayList<>();

		// Connection to the database
		this.connect();
		// Statements
		try {
			// Prepare Statement
			Statement statement = this.connection.createStatement();
			// Execute Query
			ResultSet result = statement.executeQuery(PLAYER_REQUEST);
			// Retrieve Result
			while(result.next()){
				//Retrieve by column name
				int _id  = result.getInt("p_id");
				int _number = result.getInt("p_num");
				String _name = result.getString("p_name");
				int _team = result.getInt("p_actual_team");
				// Create object
				Player p = new Player(_id, _number, _name, _team);
				players.add(p);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Closing Connection to the database
		this.close();

		return players;
	}

	/** Get all the Teams of the Database */
	public ArrayList<Team> requestTeams(ArrayList<Player> players){

		ArrayList<Team> teams = new ArrayList<Team>();

		// Connection to the database
		this.connect();
		// Statements
		try {
			// Prepare Statement
			Statement statement = (Statement) this.connection.createStatement();
			// Execute Query
			ResultSet result = statement.executeQuery(TEAM_REQUEST);
			// Retrieve Result
			while(result.next()){
				//Retrieve by column name
				int _id  = result.getInt("t_id");
				String _name = result.getString("t_name");
				String _city = result.getString("t_city");
				
				// Get Players of the Team
				ArrayList<Player> _list = new ArrayList<>();
				for(int i=0; i<players.size(); i++){
					if(players.get(i).team() == _id) {
						_list.add(players.get(i));
					}
				};
				
				// Create object
				Team t = new Team(_id, _name, _city, _list);
				teams.add(t);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Closing Connection to the database
		this.close();

		return teams;
	}
	
	/** Get all the Matches of the Database */
	public ArrayList<Match> requestMatches(ArrayList<Team> teams){

		ArrayList<Match> matches = new ArrayList<Match>();

		// Connection to the database
		this.connect();
		// Statements
		try {
			// Prepare Statement
			Statement statement = (Statement) this.connection.createStatement();
			// Execute Query
			ResultSet result = statement.executeQuery(MATCH_REQUEST);
			// Retrieve Result
			while(result.next()){
				//Retrieve by column name
				int _id  = result.getInt("m_id");
				Date _date = result.getDate("m_date");
				int _team_first = result.getInt("m_first_team");
				int _team_second = result.getInt("m_second_team");
				int _score_first = result.getInt("m_score_first");
				int _score_second = result.getInt("m_score_second");
				double _latitude = result.getDouble("m_latitude");
				double _longitude = result.getDouble("m_longitude");
				
				Team first = null;
				Team second = null;
				for(int i = 0; i< teams.size(); i++) {
					if(teams.get(i).id() == _team_first) {
						first = teams.get(i);
					}
					if(teams.get(i).id() == _team_second) {
						second = teams.get(i);
					}
				}
				
				// Create object
				Match m = new Match(_id, first, second, _score_first, _score_second, _latitude, _longitude, _date);
				matches.add(m);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Closing Connection to the database
		this.close();

		return matches;
	}

	/** Get all the Actions of the Database */
	public ArrayList<Action> requestActions(ArrayList<Player> players, ArrayList<Team> teams, ArrayList<Match> matches){
		
		ArrayList<Action> actions = new ArrayList<Action>();

		// Connection to the database
		this.connect();
		// Statements
		try {
			// Prepare Statement
			Statement statement = (Statement) this.connection.createStatement();
			// Execute Query
			ResultSet result = statement.executeQuery(ACTION_REQUEST);
			// Retrieve Result
			while(result.next()){
				//Retrieve by column name
				int _id  = result.getInt("a_id");
				int _player = result.getInt("a_player");
				int _team  = result.getInt("a_team");
				int _match = result.getInt("a_match");
				int _score = result.getInt("a_score");
				int _time = result.getInt("a_time");
				int _faults = result.getInt("a_faults");
				
				Team team = null;
				Player player = null;
				Match match = null;
				for(int i = 0; i< players.size(); i++) {
					if(players.get(i).id() == _player) {player = players.get(i);}
				}
				for(int i = 0; i< teams.size(); i++) {
					if(teams.get(i).id() == _team) {team = teams.get(i);}
				}
				for(int i = 0; i< matches.size(); i++) {
					if(matches.get(i).id() == _match) {match = matches.get(i);}
				}
				
				// Create object
				Action a = new Action(_id, player, team, match, _score, _time, _faults);
				actions.add(a);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Closing Connection to the database
		this.close();

		return actions;
	}

	public void insertTeam(Team t){
		this.connect();
		// Get Team Count

		// Statements
		try {
			// Prepare Statement
			Statement statement = (Statement) this.connection.createStatement();
			// Execute Query
			ResultSet result = statement.executeQuery("select count(*) as amount from teams");
			result.next();
			int id = result.getInt("amount") + 1;
			PreparedStatement statement1 = connection.prepareStatement(TEAM_INSERT);
			statement1.setInt(1, id);
			statement1.setString(2, t.name());
			statement1.setString(3, t.city());
			statement1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.close();
	}

	public ArrayList<Team> requestTeamsNames() {
		this.connect();
		// ARray list to return
		ArrayList<Team> teams = new ArrayList<>();

		// Statements
		try {
			// Prepare Statement
			Statement statement = (Statement) this.connection.createStatement();
			// Execute Query
			ResultSet result = statement.executeQuery("select * from teams");

			while(result.next()){
				//Retrieve by column name
				int _id  = result.getInt("t_id");
				String _name = result.getString("t_name");
				String _city = result.getString("t_city");
				Team t = new Team(_id, _name, _city, null);
				teams.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.close();
		return teams;
	}

	public void insertPlayer(Player p, String team){
		this.connect();
		// Get Team Count

		// Statements
		try {
			// Prepare Statement
			Statement statement = (Statement) this.connection.createStatement();
			// Execute Query
			ResultSet result = statement.executeQuery("select count(*) as amount from players");
			result.next();
			int p_id = result.getInt("amount") + 1;
			// Execute Query
			Log.d("TEAM = ", team);
			PreparedStatement preparedStatement = this.connection.prepareStatement("select t_id from teams where t_name = ?");
			preparedStatement.setString(1, team);
			result = preparedStatement.executeQuery();
			result.next();
			int t_id = result.getInt("t_id");
			PreparedStatement statement1 = connection.prepareStatement(PLAYER_INSERT);
			statement1.setInt(1, p_id);
			statement1.setInt(2, p.number());
			statement1.setString(3, p.name());
			statement1.setInt(4, t_id);
			statement1.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.close();
	}
}
