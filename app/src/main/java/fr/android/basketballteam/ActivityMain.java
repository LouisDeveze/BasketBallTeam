package fr.android.basketballteam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import fr.android.basketballteam.database.DatabaseManager;
import fr.android.basketballteam.gallery.FragmentGallery;
import fr.android.basketballteam.gallery.GalleryFragListener;
import fr.android.basketballteam.home.FragmentHome;
import fr.android.basketballteam.home.HomeFragListener;
import fr.android.basketballteam.match.FragmentMatch;
import fr.android.basketballteam.match.FragmentMatchPage;
import fr.android.basketballteam.match.MatchFragListener;
import fr.android.basketballteam.map.FragmentOptions;
import fr.android.basketballteam.map.OptionsFragListener;
import fr.android.basketballteam.model.Player;
import fr.android.basketballteam.model.Team;
import fr.android.basketballteam.team.AsyncPlayerInsert;
import fr.android.basketballteam.team.AsyncTeamInsert;
import fr.android.basketballteam.team.AsyncTeamSpinnerLoader;
import fr.android.basketballteam.team.FragmentTeam;
import fr.android.basketballteam.team.FragmentTeamAdder;
import fr.android.basketballteam.team.TeamFragListener;
import fr.android.basketballteam.toolbar.FragmentToolBar;
import fr.android.basketballteam.toolbar.ToolbarFragListener;

public class ActivityMain extends AppCompatActivity implements ToolbarFragListener, HomeFragListener, TeamFragListener, MatchFragListener, OptionsFragListener, GalleryFragListener {

    /**
     * Fragment Toolbar Main
     */
    private FragmentToolBar toolBar;

    /**
     * Fragment Content Home
     */
    private FragmentHome home;
    /**
     * Fragment Content Team
     */
    private FragmentTeam team;
    /**
     * Fragment Content Gallery
     */
    private FragmentGallery gallery;
    /**
     * Fragment Content Match
     */
    private FragmentMatch match;
    /**
     * Fragment Content Map
     */
    private FragmentOptions options;

    /**
     * Fragment Content TeamAdder
     */
    private FragmentTeamAdder teamAdder;

    /**
     * Fragment Content MatchPage
     */
    private FragmentMatchPage matchPage;

    /**
     * Fragment Content Current
     */
    private Fragment content;

    /**
     * Fragment Manager
     */
    private FragmentManager manager;

    private int match_id = 0;

    private String currentPhotoPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve Instance State
        String contentTab;
        if (savedInstanceState != null) {
            contentTab = savedInstanceState.getString("content");
            Log.d("ACTIVITY BUNDLE", String.valueOf(match_id));
            match_id = savedInstanceState.getInt("match_id");
        } else {
            contentTab = null;
        }

        // Creating Fragments
        this.toolBar = new FragmentToolBar();
        this.gallery = new FragmentGallery();
        this.team = new FragmentTeam();
        this.options = new FragmentOptions();
        this.match = new FragmentMatch();
        this.home = new FragmentHome();
        this.teamAdder = new FragmentTeamAdder();
        this.matchPage = new FragmentMatchPage();
        this.matchPage.setMatch(match_id);

        if (contentTab == null) {
            this.content = this.home;
        } else if (contentTab.equals("gallery")) {
            this.content = this.gallery;
        } else if (contentTab.equals("match")) {
            this.content = this.match;
        } else if (contentTab.equals("match_page")) {
            this.content = this.matchPage;
        } else if (contentTab.equals("team")) {
            this.content = this.team;
        }  else if (contentTab.equals("team_adder")) {
            this.content = this.teamAdder;
        } else if (contentTab.equals("options")) {
            this.content = this.options;
        } else if (contentTab.equals("home")) {
            this.content = this.home;
        }
        // Retrieve Manager and remove fragment unused
        this.manager = getSupportFragmentManager();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            this.manager.beginTransaction().remove(fragment).commit();
        }
        // Commit the Fragments using the manager
        this.manager.beginTransaction().add(R.id.toolbarFrame, this.toolBar).commit();
        this.manager.beginTransaction().add(R.id.contentFrame, this.content).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.content == this.gallery) {
            outState.putString("content", "gallery");
        }
        if (this.content == this.team) {
            outState.putString("content", "team");
        }
        if (this.content == this.home) {
            outState.putString("content", "home");
        }
        if (this.content == this.match) {
            outState.putString("content", "match");
        }
        if (this.content == this.matchPage) {
            outState.putString("content", "match_page");
        }
        if (this.content == this.options) {
            outState.putString("content", "options");
        }
        if (this.content == this.teamAdder) {
            outState.putString("content", "team_adder");
        }

        outState.putInt("match_id", match_id);
    }

    @Override
    public void onFragmentGallerySelected() {
        if (this.content != this.gallery) {
            this.manager.beginTransaction().remove(this.content).commit();
            this.content = this.gallery;
            this.manager.beginTransaction().add(R.id.contentFrame, this.content).commit();
        }
    }

    @Override
    public void onFragmentTeamSelected() {
        if (this.content != this.team) {
            this.manager.beginTransaction().remove(this.content).commit();
            this.content = this.team;
            this.manager.beginTransaction().add(R.id.contentFrame, this.content).commit();
        }
    }

    @Override
    public void onFragmentHomeSelected() {
        if (this.content != this.home) {
            this.manager.beginTransaction().remove(this.content).commit();
            this.content = this.home;
            this.manager.beginTransaction().add(R.id.contentFrame, this.content).commit();
        }
    }

    @Override
    public void onFragmentMatchSelected() {
        if (this.content != this.match) {
            this.manager.beginTransaction().remove(this.content).commit();
            this.content = this.match;
            this.manager.beginTransaction().add(R.id.contentFrame, this.content).commit();
        }
    }

    @Override
    public void onFragmentMapSelected() {
        if (this.content != this.options) {
            this.manager.beginTransaction().remove(this.content).commit();
            this.content = this.options;
            this.manager.beginTransaction().add(R.id.contentFrame, this.content).commit();
        }
    }

    @Override
    public void onFragmentTeamAdderSelected() {
        this.manager.beginTransaction().remove(this.content).commit();
        this.content = this.teamAdder;
        this.manager.beginTransaction().add(R.id.contentFrame, this.content).commit();
    }


    @Override
    public void onMatchPageFragmentSelected(int id) {
        this.match_id = id;
        this.manager.beginTransaction().remove(this.content).commit();
        this.content = this.matchPage;
        this.manager.beginTransaction().add(R.id.contentFrame, this.content).commit();
        this.matchPage.setMatch(id);
    }

    @Override
    public void onMatchCamera(int id) {

        Log.d("File", "Coucou");
        dispatchTakePictureIntent();
        galleryAddPic();
    }

    @Override
    public void onMatchPin(int id) {

    }

    @Override
    public void onFragmentTeamAdded(Team team) {
        new AsyncTeamInsert().execute(team);
        Toast.makeText(getApplicationContext(), R.string.operation_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentPlayerAdded(Player p, String team) {
        new AsyncPlayerInsert(p, team).execute();
        Toast.makeText(getApplicationContext(), R.string.operation_success, Toast.LENGTH_SHORT).show();
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 1);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.FRANCE).format(new Date());
        String imageFileName = "BasketBall_" + match_id + "_"+timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg",storageDir );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Log.d("GALLERY", "ADD PICTURE");
            this.galleryAddPic();
        }
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, ActivityMain.class);
        finish();
        startActivity(refresh);
    }

}
