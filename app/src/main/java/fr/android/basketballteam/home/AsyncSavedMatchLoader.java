package fr.android.basketballteam.home;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.sql.Date;
import java.util.ArrayList;

import fr.android.basketballteam.R;
import fr.android.basketballteam.database.DatabaseOpenHelper;
import fr.android.basketballteam.match.MatchFragListener;
import fr.android.basketballteam.model.Match;
import fr.android.basketballteam.model.MatchLite;

public class AsyncSavedMatchLoader extends AsyncTask<Integer, Integer, ArrayList<MatchLite>> {

    private LinearLayout table;
    private Context context;
    private LayoutInflater inflater;
    private SQLiteDatabase db;
    private MatchFragListener activityMain;

    public AsyncSavedMatchLoader(Context context, LinearLayout table, LayoutInflater inflater, MatchFragListener activityMain) {
        this.context = context;
        this.table = table;
        this.inflater = inflater;
        this.activityMain = activityMain;
    }

    @Override
    protected ArrayList<MatchLite> doInBackground(Integer... integers) {

        ArrayList<MatchLite> matches = new ArrayList<>();
        this.db = DatabaseOpenHelper.getInstance(context).getWritableDatabase();
        Cursor c  = db.rawQuery("select * from matches;", null);
        while (c .moveToNext()) {
            int m_id = c .getInt(c.getColumnIndex("m_id"));
            Date m_date = Date.valueOf(c.getString(c.getColumnIndex("m_date")));
            String m_first = c.getString(c.getColumnIndex("m_first_team"));
            String m_second = c.getString(c.getColumnIndex("m_second_team"));
            int m_score_first = c.getInt(c.getColumnIndex("m_score_first"));
            int m_score_second = c.getInt(c.getColumnIndex("m_score_second"));
            int m_latitude = c .getInt(c.getColumnIndex("m_latitude"));
            int m_longitude = c .getInt(c.getColumnIndex("m_longitude"));
            matches.add(new MatchLite(m_id, m_first, m_second, m_score_first, m_score_second, m_latitude, m_longitude, m_date));

        }

        return matches;
    }

    @Override
    protected void onPostExecute(ArrayList<MatchLite> matches) {

        boolean recreate = true;
        LinearLayout row = null;
        for(final MatchLite match : matches){
            if(recreate){
                recreate = false;
                row = new LinearLayout(context);
                row.setOrientation(LinearLayout.HORIZONTAL);
                Button button = (Button)inflater.inflate(R.layout.local_match_item, null);
                String title = match.first() + "\nVS\n" + match.second() + "\n";
                button.setText(title);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                params.setMargins(30, 20, 20, 10);
                button.setLayoutParams(params);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityMain.onMatchPageFragmentSelected(match.id());
                    }
                });

                row.addView(button);
            }else{
                recreate = true;
                Button button = (Button)inflater.inflate(R.layout.local_match_item, null);
                String title = match.first() + "\nVS\n" + match.second() + "\n";
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                button.setText(title);
                params.setMargins(20, 20, 30, 10);
                button.setLayoutParams(params);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activityMain.onMatchPageFragmentSelected(match.id());
                    }
                });
                row.addView(button);
                table.addView(row);
            }
        }

        if(!recreate){
            LinearLayout v = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            params.setMargins(20, 20, 30, 10);
            v.setLayoutParams(params);
            row.addView(v);
            table.addView(row);
        }

        this.db.close();
    }
}
