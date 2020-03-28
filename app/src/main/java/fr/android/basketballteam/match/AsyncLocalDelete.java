package fr.android.basketballteam.match;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fr.android.basketballteam.R;
import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.database.DatabaseOpenHelper;
import fr.android.basketballteam.model.Action;
import fr.android.basketballteam.model.ActionLite;
import fr.android.basketballteam.model.Match;
import fr.android.basketballteam.model.MatchLite;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;

public class AsyncLocalDelete extends AsyncTask<Integer, Integer, Boolean> {

    private Context context;
    private SQLiteDatabase db;
    private FloatingActionButton download;

    public AsyncLocalDelete(Context context, FloatingActionButton download){
        this.context = context;
        this.download = download;
    }

    @Override
    protected Boolean doInBackground(Integer... match_id) {
        // Get Databases
        db = DatabaseOpenHelper.getInstance(context).getWritableDatabase();
        db.execSQL("delete from actions where a_match = " + match_id[0]);
        Log.d("SQLite", "Delete Match");
        db.execSQL("delete from matches where m_id = " + match_id[0]);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean b){
        download.setImageDrawable(context.getResources().getDrawable(R.drawable.download, null));
        db.close();
    }
}
