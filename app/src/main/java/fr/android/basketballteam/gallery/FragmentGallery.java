package fr.android.basketballteam.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.android.basketballteam.R;

public class FragmentGallery extends Fragment {

    private GalleryFragListener activityMain;

    private LinearLayout layout;

    /** Constructor */
    public FragmentGallery(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);
        layout = view.findViewById(R.id.galleryContent);

        File[] files =  getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).listFiles();

        boolean recreate = true;
        LinearLayout row = null;

        for(File file : files){
            if(recreate){
                recreate = false;
                // Create Row
                row = new LinearLayout(getContext());
                row.setOrientation(LinearLayout.HORIZONTAL);
                // Create Picture
                LinearLayout pictureLayout = (LinearLayout)inflater.inflate(R.layout.gallery_item, null);
                TextView text = pictureLayout.findViewById(R.id.match_name);
                ImageView image = pictureLayout.findViewById(R.id.match_picture);

                String title = "Match " + file.getAbsolutePath().split("_")[1];
                text.setText(title);
                Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                image.setImageBitmap(bmp);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                params.setMargins(20, 10, 10, 10);
                pictureLayout.setLayoutParams(params);
                // Add to row
                row.addView(pictureLayout);

            }else{
                recreate = true;

                // Create Picture
                LinearLayout pictureLayout = (LinearLayout)inflater.inflate(R.layout.gallery_item, null);
                TextView text = pictureLayout.findViewById(R.id.match_name);
                ImageView image = pictureLayout.findViewById(R.id.match_picture);
                String title = "Match " + file.getAbsolutePath().split("_")[1];
                text.setText(title);
                Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                image.setImageBitmap(bmp);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
                params.setMargins(10, 10, 20, 10);
                pictureLayout.setLayoutParams(params);
                // Add to row
                row.addView(pictureLayout);
                // Add Row to picture
                layout.addView(row);
            }
        }

        if(!recreate){
            // Create Picture
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            params.setMargins(10, 10, 20, 10);
            LinearLayout dummy = new LinearLayout(getContext());
            dummy.setLayoutParams(params);
            dummy.setBackgroundColor(Color.RED);
            // Add to row
            row.addView(dummy);
            // Add Row to picture
            layout.addView(row);
        }

        // View Creation
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GalleryFragListener) {
            activityMain = (GalleryFragListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement GalleryFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityMain = null;
    }
}
