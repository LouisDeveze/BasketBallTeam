package fr.android.basketballteam.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "basketball";
    private final static int DB_VERSION = 1;

    private final String CREATE_TABLE_MATCHES = "create table matches (m_id int not null , m_date varchar(100), m_first_team varchar(100), m_second_team varchar(100), m_winner varchar(100), m_score_first int, m_score_second int, m_latitude double, m_longitude double, constraint matches_pk primary key (m_id));";
    private final String CREATE_TABLE_ACTIONS = "create table actions (a_id int not null , a_player int, a_team int, a_match int, a_score int, a_time int, a_faults int, constraint actions_pk primary key (a_id) );";

    private static DatabaseOpenHelper instance;

    public static synchronized DatabaseOpenHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseOpenHelper(context);
        }
        return instance;
    }

    public DatabaseOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MATCHES);
        db.execSQL(CREATE_TABLE_ACTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("drop table if exists teams");
        db.execSQL("drop table if exists players");
        db.execSQL("drop table if exists matches");
        db.execSQL("drop table if exists actions");
        // Create tables again
        onCreate(db);
    }
}
